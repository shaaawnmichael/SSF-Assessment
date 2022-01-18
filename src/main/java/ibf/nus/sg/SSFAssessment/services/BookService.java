package ibf.nus.sg.SSFAssessment.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf.nus.sg.SSFAssessment.SsfAssessmentApplication;
import ibf.nus.sg.SSFAssessment.model.Book;
import ibf.nus.sg.SSFAssessment.model.Library;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import static ibf.nus.sg.SSFAssessment.constants.*;

@Service
public class BookService {

    private final Logger logger = Logger.getLogger(SsfAssessmentApplication.class.getName());

    public List<Library> search(String searchTitle) {
        
                // GET with query parameters
                final String url = UriComponentsBuilder
                .fromUriString(URL_OPENLIBRARY)
                .queryParam("q", searchTitle.trim().replace(" ", "+")) //add query par
                .queryParam("limit", 20)
                .toUriString();

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK)
            throw new IllegalArgumentException(
                "Error: status code %s".formatted(resp.getStatusCode().toString())
            );
        final String body = resp.getBody();

        //logger.log(Level.INFO, "payload: %s".formatted(body));

        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();
            final JsonArray searchResults = result.getJsonArray("docs");
            return searchResults.stream()
                .map(b -> (JsonObject) b)
                .map(Library::create)
                .collect(Collectors.toList());

        } catch (Exception ex) { 
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public Book bookDetails(String work_ID) throws IOException {

        final String url2 = "https://openlibrary.org/works/" + work_ID + ".json";

        final RequestEntity<Void> req2 = RequestEntity.get(url2).build();
    final RestTemplate template2 = new RestTemplate();
    final ResponseEntity<String> resp2 = template2.exchange(req2, String.class);

    if (resp2.getStatusCode() != HttpStatus.OK)
        throw new IllegalArgumentException(
            "Error: status code %s".formatted(resp2.getStatusCode().toString())
        );
    final String body = resp2.getBody();

    //logger.log(Level.INFO, "payload: %s".formatted(body));

    try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
        final JsonReader reader = Json.createReader(is);
        final JsonObject result = reader.readObject();

        final Book book_view = new Book();
                book_view.setTitle(null);
                book_view.setDescription(null);

                
                book_view.setTitle(result.getString("title"));
                logger.log(Level.INFO, "Title  %s".formatted(book_view.getTitle()));
                book_view.setDescription(result.getString("description"));
                logger.log(Level.INFO, "Description  %s".formatted(book_view.getDescription()));
                
                return book_view; 

            }   catch (Exception ex) { 
                ex.printStackTrace();
            }


        return null;
        
    }   
    
} 

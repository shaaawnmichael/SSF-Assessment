package ibf.nus.sg.SSFAssessment.model;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Library {
    
    private String bookTitle;
    private String WorksId;
    private String Link;

    public String getLink() {
        return this.Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }
    
    public String getWorksId() {
        return this.WorksId;
    }

    public void setWorksId(String WorksId) {
        this.WorksId = WorksId;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public static Library create(JsonObject o) {
        final Library result = new Library();
        result.setBookTitle(o.getString("title"));
        result.setWorksId(o.getString("key"));
        result.setLink("/book/"+o.getString("key").split("/")[2]);
        return result;
    }
    public static Library create(String jsonString) {
        try (InputStream is = new ByteArrayInputStream(jsonString.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            return create(reader.readObject());
        } catch (Exception ex) { }

        // Need to handle error
        return new Library();
    }
    
    @Override
    public String toString() {
        return this.toJson().toString();
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("title", bookTitle)
            .add("works_id", WorksId)
            .build();
    }


} 

    

package ibf.nus.sg.SSFAssessment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf.nus.sg.SSFAssessment.model.Book;
import ibf.nus.sg.SSFAssessment.services.BookService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/book", produces = MediaType.TEXT_HTML_VALUE)
public class BookController {
    
    private final Logger logger = Logger.getLogger(BookController.class.getName());

    @Autowired
    private BookService bookService;

    @GetMapping(path = "{worksID}")
    public String getBookById(@PathVariable String worksID, Model model) {
        try {
            Book book_view = bookService.bookDetails(worksID);
            logger.log(Level.INFO, "worksid to query => %s".formatted(worksID));

            model.addAttribute("book_view", book_view);
            return "bookresult";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}

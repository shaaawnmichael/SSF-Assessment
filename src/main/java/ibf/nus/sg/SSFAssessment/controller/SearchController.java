package ibf.nus.sg.SSFAssessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ibf.nus.sg.SSFAssessment.SsfAssessmentApplication;
import ibf.nus.sg.SSFAssessment.model.Library;
import ibf.nus.sg.SSFAssessment.services.BookService;

@Controller
@RequestMapping(path = "/book")
public class SearchController {
    
    private final Logger logger = Logger.getLogger(SsfAssessmentApplication.class.getName());

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getBook(@RequestParam String bookTitle, Model model) throws IOException {
        logger.log(Level.INFO, "Book title: %s".formatted(bookTitle));
        //model.addAttribute("bookTitle", bookTitle);
        
        List<Library> bookResults=Collections.emptyList();;
        bookResults = bookService.search(bookTitle);
        logger.log(Level.INFO, "Book result: %s".formatted(bookResults));
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("results", bookResults);
        return "results";

        

    }


}

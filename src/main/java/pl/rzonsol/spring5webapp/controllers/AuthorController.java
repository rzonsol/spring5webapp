package pl.rzonsol.spring5webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rzonsol.spring5webapp.repositories.AuthorRepository;

/**
 * Created by rzonsol on 20.08.2017.
 */
@Controller
public class AuthorController {

    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model){
            model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }
}

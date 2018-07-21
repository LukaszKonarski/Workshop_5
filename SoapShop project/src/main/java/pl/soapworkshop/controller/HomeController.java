package pl.soapworkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.soapworkshop.repository.CategoryRepository;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public HomeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("categories", this.categoryRepository.findAll());
        return "index";
    }


}

package by.petrovlad.ntickets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        model.addAttribute("name", name);
        return "main";
    }

}
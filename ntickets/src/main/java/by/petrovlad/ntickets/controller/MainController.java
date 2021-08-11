package by.petrovlad.ntickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/main")
public class MainController {


    @GetMapping()
    public String main(Model model, HttpServletRequest request) {

        return "";
    }

}
package fr.diginamic.spring.demo.controleur;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControleur {
    @GetMapping
    public String direHello(){
        return "Hello";
    }
    @GetMapping("/salut")
    public String direSalut(){
        return "Salut";
    }
}

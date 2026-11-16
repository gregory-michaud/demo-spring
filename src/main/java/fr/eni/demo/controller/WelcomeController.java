package fr.eni.demo.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "welcomeControllerBean")
@Scope("prototype")
public class WelcomeController {

    public void welcome(){
        System.out.println("welcome!");
    }



}

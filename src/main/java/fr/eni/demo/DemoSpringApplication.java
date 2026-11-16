package fr.eni.demo;

import fr.eni.demo.controller.FormateurController;
import fr.eni.demo.controller.WelcomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoSpringApplication.class, args);

        WelcomeController welcomeController = (WelcomeController) applicationContext.getBean("welcomeControllerBean");
        welcomeController.welcome();

        WelcomeController welcomeController1 = applicationContext.getBean(WelcomeController.class);
        welcomeController1.welcome();

        System.out.println("welcomeController = " + welcomeController);
        System.out.println("welcomeController1 = " + welcomeController1);


        FormateurController formateurController = applicationContext.getBean(FormateurController.class);
        formateurController.afficherFormateurs();


    }

}

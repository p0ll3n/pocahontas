package com.kytiba.poc.pocahontas;

import javax.annotation.Nonnull;
import com.kyriba.poc.PocService;
import com.kyriba.poc.PocService2;
import com.kyriba.poc.Result;
import com.kyriba.poc.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.kyriba.poc")
@SpringBootApplication
public class PocahontasApplication implements CommandLineRunner {

    @Autowired
    private PocService pocService;

    @Autowired
    private PocService2 pocService2;

    public static void main(String[] args) {
        SpringApplication.run(PocahontasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        displayCapitalizedUsingpocService("blabla");
        displayCapitalizedUsingpocService("");
        displayCapitalizedUsingpocService2("blabla");
        displayCapitalizedUsingpocService2("");

    }

    public void displayCapitalizedUsingpocService(@Nonnull final String input){
        try {
            System.out.println(pocService.capitalize(input));
        } catch (ValidationException ve){
            System.out.println(ve.getMessage());
        }
    }
    public void displayCapitalizedUsingpocService2(@Nonnull final String input){
        final Result<String> res = pocService2.capitalize(input);
        if(res.getValue().isPresent()){
            System.out.println(res.getValue().get());
        } else if (res.getErrorMessage().isPresent()){
            System.out.println(res.getErrorMessage().get());
        } else {
            System.out.println("????");
        }
    }
}

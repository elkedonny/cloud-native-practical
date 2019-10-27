package com.ezgroceries.cocktail;

import feign.Feign;
import feign.FeignException;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(produces = "application/json")
public class CocktailController {

    CocktailDBClient myCocktailClient;

    public CocktailController () {
        myCocktailClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(CocktailDBClient.class))
                .logLevel(Logger.Level.FULL)
                .target(CocktailDBClient.class, "https://www.thecocktaildb.com/api/json/v1/1");
    }

    @GetMapping (value = "/cocktails")
    public CocktailDBResponse get(String search) {
        try {
            return myCocktailClient.searchCocktails(search);
        } catch (FeignException fe) {
            System.out.println("Got a FeignException !");
            throw fe;
        }

    }



}
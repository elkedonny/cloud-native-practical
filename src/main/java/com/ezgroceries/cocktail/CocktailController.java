package com.ezgroceries.cocktail;

import feign.Feign;
import feign.FeignException;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(produces = "application/json")
public class CocktailController {

    CocktailDBClient myCocktailClient;
    CocktailService cocktailDBService;

    public CocktailController () {

        // This will allow access to the external cocktail API
        myCocktailClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(CocktailDBClient.class))
                .logLevel(Logger.Level.FULL)
                .target(CocktailDBClient.class, "https://www.thecocktaildb.com/api/json/v1/1");

        // This service will access our own repository for db persistence
        // todo get the cocktailRepo from the applicationcontext
        cocktailDBService = new CocktailService();
    }

    @GetMapping (value = "/cocktails")
    public CocktailDBResponse get(String search) {
        try {
            // Fetch external cocktail resources
            CocktailDBResponse cocktailResults = myCocktailClient.searchCocktails(search);
            for (CocktailDBResponse.DrinkResource drink : cocktailResults.getDrinks()) {
                // TODO find out how to handle the ingredients
                // TODO only store the cocktails we don't have yet
                cocktailDBService.create(new CocktailResource(drink.getIdDrink(), drink.getStrDrink(), drink.getStrGlass(), drink.getStrGlass(), drink.getStrDrinkThumb(), null));

            }

            return myCocktailClient.searchCocktails(search);
        } catch (FeignException fe) {
            System.out.println("Got a FeignException !");
            throw fe;
        }

    }



}
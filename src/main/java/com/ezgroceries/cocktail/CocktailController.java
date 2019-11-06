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
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {


    CocktailService cocktailDBService;

    public CocktailController () {

        // This service will access our own repository for db persistence
        // TODO get the cocktailRepo from the applicationcontext ? will it autowire ?
        cocktailDBService = new CocktailService();
    }

    @GetMapping (value = "/")
    public CocktailDBResponse get(String search) {
        return cocktailDBService.getSomeCocktails(search);
    }



}
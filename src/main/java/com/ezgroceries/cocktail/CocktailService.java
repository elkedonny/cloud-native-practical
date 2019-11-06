package com.ezgroceries.cocktail;

import feign.Feign;
import feign.FeignException;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CocktailService {

    private CocktailRepository cocktailRepository;
    CocktailDBClient myCocktailClient;

    public CocktailService() {
        createCocktailClient();
    }
    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
        createCocktailClient();
    }

    private void createCocktailClient() {
        // This will allow access to the external cocktail API
        myCocktailClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(CocktailDBClient.class))
                .logLevel(Logger.Level.FULL)
                .target(CocktailDBClient.class, "https://www.thecocktaildb.com/api/json/v1/1");
    }

    public CocktailResource create(CocktailResource cocktailResource) {
        return cocktailRepository.save(cocktailResource);
    }

    public CocktailDBResponse getSomeCocktails(String search) {
        try {
            // Fetch external cocktail resources
            CocktailDBResponse cocktailResults = myCocktailClient.searchCocktails(search);
            for (CocktailDBResponse.DrinkResource drink : cocktailResults.getDrinks()) {
                // TODO find out how to handle the ingredients
                // TODO only store the cocktails we don't have yet
                create(new CocktailResource(drink.getIdDrink(), drink.getStrDrink(), drink.getStrGlass(), drink.getStrGlass(), drink.getStrDrinkThumb(), null));

            }

            return myCocktailClient.searchCocktails(search);
        } catch (
                FeignException fe) {
            System.out.println("Got a FeignException !");
            throw fe;
        }

    }
    /*
    public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailResource> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream().collect(Collectors.toMap(CocktailResource::getIdDrink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailResource> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailResource cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailResource newCocktailEntity = new CocktailResource();
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailResource::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailResource> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getIdDrink(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), drinkResource.getIngredients())).collect(Collectors.toList());
    }
    */


}


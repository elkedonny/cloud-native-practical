package com.ezgroceries.coctail;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(produces = "application/json")
public class CocktailController {

    @GetMapping (value = "/cocktails")
    public List<Cocktail> get(@RequestParam String search) {
        return getDummyResources();
    }

    private List<Cocktail> getDummyResources() {
        ArrayList<String> ingList1 = new ArrayList<String>();
        ingList1.add("Tequila");
        ingList1.add("Triple Sec");
        ingList1.add("Lime Juice");
        ingList1.add("Salt");

        ArrayList<String> ingList2 = new ArrayList<String>();
        ingList2.add("Tequila");
        ingList2.add("Blue Curacao");
        ingList2.add("Lime Juice");
        ingList2.add("Salt");

        return Arrays.asList(
                new Cocktail(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        ingList1),
                new Cocktail(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                "Cocktail glass",
                "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        ingList2));
    }
}
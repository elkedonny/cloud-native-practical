package com.ezgroceries.shoppinglist;

import com.ezgroceries.coctail.Cocktail;
import com.ezgroceries.coctail.CocktailController;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {

    @GetMapping
    public List<ShoppingList> get(@RequestParam String search) {
        return getDummyResources();
    }


    private List<ShoppingList> getDummyResources() {

        return Arrays.asList(
                new ShoppingList(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c5"), "Stephs birthday",
                        new ArrayList<Cocktail>()),
                new ShoppingList(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c6"), "Jeans birthday",
                        new ArrayList<Cocktail>()));

    }




}
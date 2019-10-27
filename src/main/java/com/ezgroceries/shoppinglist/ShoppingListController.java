package com.ezgroceries.shoppinglist;

import com.ezgroceries.cocktail.Cocktail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequestMapping(produces = "application/json")
public class ShoppingListController {

    @GetMapping(value = "/shopping-lists/{uuid}")
    public ShoppingList get(@PathVariable UUID uuid) {
        // Returns dummy data for now
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Gin"); ingredients.add("Triple sec"); ingredients.add("Lime juice");
        ingredients.add("Strawberry"); ingredients.add("Blue curacao"); ingredients.add("Tequila");
        return new ShoppingList(uuid, "Ingredient list", ingredients);
    }

    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)  // 201
    public @ResponseBody ShoppingList createShoppingList(@RequestBody ShoppingList newShoppingList) {
        newShoppingList.setShoppingListId(UUID.randomUUID());
        //return entityWithLocation(newShoppingList.getShoppingListId());
        return newShoppingList;
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)  // 201
    public @ResponseBody ArrayList<UUID> addCocktailToShoppingList(@PathVariable UUID shoppingListId, @RequestBody ArrayList<Cocktail> cocktails) {
        ShoppingList shopList = new ShoppingList(shoppingListId, "Elke's list", new ArrayList<String>());
        ArrayList<UUID> uuidList = new ArrayList<UUID>();
        for (Cocktail c : cocktails) {
            c.setCocktailId(UUID.randomUUID());
            shopList.addCoctail(c);
            uuidList.add(c.getCocktailId());
        }
        return uuidList;
    }


    @GetMapping(value = "/shopping-lists")
    public ArrayList<ShoppingList> getAllShoppingLists() {
            return getDummyResources();
        }

    private ArrayList<ShoppingList> getDummyResources() {

        ArrayList<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
        shoppingLists.add(new ShoppingList(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c5"), "Stephs birthday",
                        new ArrayList<String>()));
        shoppingLists.add(new ShoppingList(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c6"), "Jeans birthday",
                        new ArrayList<String>()));
        return shoppingLists;
    }

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{childId}").buildAndExpand(resourceId).toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleBadInput(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        return ResponseEntity.badRequest().build();
    }


}
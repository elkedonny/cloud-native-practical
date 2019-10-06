package com.ezgroceries.shoppinglist;

import com.ezgroceries.coctail.Cocktail;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID shoppingListId;
    private String name;
    private ArrayList<String> ingredients;

    public ShoppingList(UUID shoppingListId, String name, ArrayList<String> ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addCoctail(Cocktail cocktail) {
        if (cocktail.getIngredients() != null) this.ingredients.addAll(cocktail.getIngredients());
    }

}

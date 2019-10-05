package com.ezgroceries.shoppinglist;

import com.ezgroceries.coctail.Cocktail;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ShoppingList {

    @Id
    private UUID shoppingListId;
    private String name;
    private ArrayList<Cocktail> cocktails;

    public ShoppingList(UUID shoppingListId, String name, ArrayList<Cocktail> cocktails) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.cocktails = cocktails;
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

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(ArrayList<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }
}

package com.ezgroceries.shoppinglist;

import com.ezgroceries.cocktail.Cocktail;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SHOPPING_LIST")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   /* AUTO   SEQUENCE */
    @Column(name = "ID")
    private UUID shoppingListId;
    private String name;
    private ArrayList<String> ingredients;

    //@ManyToMany(cascade = CascadeType.ALL, mappedBy = "shopping_list")
    //private Set<Cocktail> cocktails = new HashSet<Cocktail>();

    public ShoppingList() {}

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

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addCoctail(Cocktail cocktail) {
        if (cocktail.getIngredients() != null) this.ingredients.addAll(cocktail.getIngredients());
    }

    /*
    public Set<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(Set<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }
    */

}

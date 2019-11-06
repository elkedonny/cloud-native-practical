package com.ezgroceries.cocktail;

import com.ezgroceries.shoppinglist.ShoppingListResource;
import com.ezgroceries.utils.StringSetConverter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "COCKTAIL")
public class CocktailResource {

    // annotations were placed on the getter for JPA accessor mapping
    private UUID cocktailId;

    private String idDrink;
    private String name;
    private String glass;
    private String instructions;
    private String image;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    /*
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cocktail")
    @JoinTable(name = "cocktail_shopping_list",
        joinColumns = @JoinColumn(name = "cocktail_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "shopping_list_id", referencedColumnName = "id")) */
    private Set<ShoppingListResource> shoppingLists = new HashSet<ShoppingListResource>();

    public CocktailResource() {}

    public CocktailResource(String idDrink, String name, String glass, String instructions, String image, Set<String> ingredients) {
        this.idDrink = idDrink;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   /* AUTO   SEQUENCE */
    @Column(name = "ID")
    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<ShoppingListResource> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(Set<ShoppingListResource> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }
}

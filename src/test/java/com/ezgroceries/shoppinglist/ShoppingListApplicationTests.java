package com.ezgroceries.shoppinglist;



import com.ezgroceries.cocktail.CocktailResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingListController.class)
public class ShoppingListApplicationTests {

	@Autowired
  	private MockMvc mvc;


	@Test
	public void getShoppingListAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
		  .get("/shopping-lists/23b3d85a-3928-41c0-a533-6538a71e17c7")
		  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers.status().isOk())
			  .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
			  .andExpect(MockMvcResultMatchers.jsonPath("shoppingListId").value("23b3d85a-3928-41c0-a533-6538a71e17c7"));
	}


	@Test
	public void createShoppingListAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
		  .post("/shopping-lists")
		  .content(asJsonString(new ShoppingListResource(null, "Paulo's birthday", null)))
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers.status().isCreated())
			  .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
			  .andExpect(MockMvcResultMatchers.jsonPath("$.shoppingListId").exists());
	}

	@Test
	public void addCocktailsToShoppingListAPI() throws Exception
	{
		ArrayList<String> ingList1 = new ArrayList<String>();
        ingList1.add("Tequila");ingList1.add("Triple Sec");ingList1.add("Lime Juice");ingList1.add("Salt");

        ArrayList<String> ingList2 = new ArrayList<String>();
        ingList2.add("Tequila");ingList2.add("Blue Curacao");ingList2.add("Lime Juice");ingList2.add("Salt");

		ArrayList<CocktailResource> cocktails = new ArrayList<CocktailResource>();
		cocktails.add(new CocktailResource("23b3d85a-3928-41c0-a533-6538a71e17c4", "Margerita",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
				new HashSet<>(ingList1)));

		cocktails.add(new CocktailResource("d615ec78-fe93-467b-8d26-5d26d8eab073", "Blue Margerita",
                "Cocktail glass",
                "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        new HashSet<>(ingList2)));

	  mvc.perform( MockMvcRequestBuilders
		  .post("/shopping-lists/23b3d85a-3928-41c0-a533-6538a71e17c7/cocktails")
		  .content(asJsonString(cocktails))
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers.status().isCreated())
			  .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
			  .andExpect(MockMvcResultMatchers.jsonPath("[0]").exists())
			  .andExpect(MockMvcResultMatchers.jsonPath("[1]").exists());
	}



	@Test
	public void getAllShoppingListsAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
		  .get("/shopping-lists")
		  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers.status().isOk())
			  .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
			  .andExpect(MockMvcResultMatchers.jsonPath("[0].shoppingListId").value("23b3d85a-3928-41c0-a533-6538a71e17c5"));
	}



	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

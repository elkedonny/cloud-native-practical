package com.ezgroceries.shoppinglist;

import com.ezgroceries.coctail.CocktailController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(CocktailController.class)
public class CoctailControllerTests {

    @Autowired
  	private MockMvc mvc;

	@Test
	public void getCocktailAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
		  .get("http://localhost:8080/cocktails?search=russian")
		  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers.status().isOk())
			  .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
			  .andExpect(MockMvcResultMatchers.jsonPath("$.drinks").exists())
			  .andExpect(MockMvcResultMatchers.jsonPath("$.drinks[0].strDrink").value("Black Russian"))
			  .andExpect(MockMvcResultMatchers.jsonPath("$.drinks[1].strDrink").value("White Russian"));

	}


}

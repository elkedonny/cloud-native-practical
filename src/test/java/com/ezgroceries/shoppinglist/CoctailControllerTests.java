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
		  .get("http://localhost:8080/cocktails?search=iets")
		  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers.status().isOk())
			  .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
			  .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("Margerita"))
			  .andExpect(MockMvcResultMatchers.jsonPath("[1].name").value("Blue Margerita"));
	}


}

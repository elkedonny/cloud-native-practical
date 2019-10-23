package com.ezgroceries.coctail;

import com.ezgroceries.config.CocktailDBClientConfig;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Resource;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", configuration= CocktailDBClientConfig.class)
public interface CocktailDBClient {

    @RequestLine("GET /search.php?s={search}")
    @Headers("Content-Type: application/json")
    CocktailDBResponse searchCocktails(@Param("search") String search);

}


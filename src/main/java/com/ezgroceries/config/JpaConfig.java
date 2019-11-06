package com.ezgroceries.config;

import com.ezgroceries.cocktail.CocktailRepository;
import com.ezgroceries.cocktail.CocktailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.ezgroceries")
public class JpaConfig {

    public CocktailService cocktailService(CocktailRepository repo) {
        return new CocktailService(repo);
    }

}





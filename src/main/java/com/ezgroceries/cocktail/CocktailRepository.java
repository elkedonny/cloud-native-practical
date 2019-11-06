package com.ezgroceries.cocktail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CocktailRepository extends JpaRepository<CocktailResource, UUID> {

    public Optional<CocktailResource> findById(UUID id);

    

}

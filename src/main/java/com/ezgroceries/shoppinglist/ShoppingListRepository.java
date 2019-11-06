package com.ezgroceries.shoppinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingListRepository extends JpaRepository<ShoppingListResource, UUID> {

    // TODO : declare query methods

}

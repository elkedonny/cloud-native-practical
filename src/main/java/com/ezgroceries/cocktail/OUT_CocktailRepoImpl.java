package com.ezgroceries.cocktail;

import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OUT_CocktailRepoImpl implements CocktailRepository {

    // Actual implementations
    @Override
    public <S extends CocktailResource> boolean exists(Example<S> example) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues()
                .withIgnorePaths("cocktailId","name", "glass", "instructions", "image", "ingredients");





        return false;
    }




    // Empty not really overridden methods

    @Override
    public void delete(CocktailResource cocktail) {

    }

    @Override
    public void deleteAll(Iterable<? extends CocktailResource> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CocktailResource> findAll() {
        return null;
    }

    @Override
    public List<CocktailResource> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CocktailResource> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CocktailResource> findAllById(Iterable<UUID> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public <S extends CocktailResource> S save(S s) {
        return null;
    }

    @Override
    public <S extends CocktailResource> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CocktailResource> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends CocktailResource> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<CocktailResource> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CocktailResource getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends CocktailResource> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CocktailResource> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CocktailResource> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CocktailResource> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CocktailResource> long count(Example<S> example) {
        return 0;
    }


}

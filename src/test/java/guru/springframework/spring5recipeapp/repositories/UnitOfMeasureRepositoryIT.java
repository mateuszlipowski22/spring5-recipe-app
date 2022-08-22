package guru.springframework.spring5recipeapp.repositories;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescription() {

        Optional<UnitOfMeasures> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getDescription());

    }

    @Test
    void findByDescriptionCup() {

        Optional<UnitOfMeasures> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", uomOptional.get().getDescription());

    }
}
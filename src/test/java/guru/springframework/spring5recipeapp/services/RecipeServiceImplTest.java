package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.Converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.Converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);

    }

    @Test
    public void getRecipeByIdTestNotFound(){
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            recipeService.findById(1L);
        });

    }

    @Test
    public void getRecipeByIdTest(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional=Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById( 1L);

        assertNotNull(recipeReturned,"Null recipe returned");
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();

    }

    @Test
    void getRecipes() {

        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();

    }

    @Test
    public void testDeleteById(){
        //given
        Long idToDelete = 2L;

        //when
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}
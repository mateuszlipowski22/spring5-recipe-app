package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final Long ID=1L;
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 1;
    public static final Integer COOK_TIME = 1;
    public static final Integer SERVINGS = 1;
    public static final String SOURCE="source";
    public static final String URL = "url";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY=Difficulty.EASY;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter=new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasuresCommandToUnitOfMeasures()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setId(ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setPrepTime(PREP_TIME);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);

        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);
        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);
        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);
        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        //then
        Recipe recipe  = converter.convert(recipeCommand);

        //when
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}
package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter=new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasuresToUnitOfMeasuresCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setDescription(DESCRIPTION);
        recipe.setId(ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setPrepTime(PREP_TIME);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID2);
        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        //then
        RecipeCommand recipeCommand  = converter.convert(recipe);

        //when
        assertNotNull(recipeCommand);
        assertEquals(ID, recipeCommand.getId());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }
}
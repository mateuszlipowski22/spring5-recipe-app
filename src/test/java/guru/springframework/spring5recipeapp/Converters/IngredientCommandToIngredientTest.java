package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Recipe REICPE = new Recipe();
    public static final BigDecimal AMMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheesburger";
    public static final Long ID_VALUE = 1L;
    public static final Long UOM_ID = 2L;

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter=new IngredientCommandToIngredient(new UnitOfMeasuresCommandToUnitOfMeasures());
    }


    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {

        //given
        IngredientCommand command=new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasuresCommand unitOfMeasuresCommand = new UnitOfMeasuresCommand();
        unitOfMeasuresCommand.setId(UOM_ID);
        command.setUom(unitOfMeasuresCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getId());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());

    }

    @Test
    public void convertWithNullUom(){
        //given
        IngredientCommand command=new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMMOUNT);
        command.setDescription(DESCRIPTION);


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMMOUNT, ingredient.getAmount());

    }
}
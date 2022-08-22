package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Recipe REICPE = new Recipe();
    public static final BigDecimal AMMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheesburger";
    public static final Long ID_VALUE = 1L;
    public static final Long UOM_ID = 2L;

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter=new IngredientToIngredientCommand(new UnitOfMeasuresToUnitOfMeasuresCommand());
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {

        //given
        Ingredient command=new Ingredient();
        command.setId(ID_VALUE);
        command.setAmount(AMMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasures unitOfMeasures = new UnitOfMeasures();
        unitOfMeasures.setId(UOM_ID);
        command.setUom(unitOfMeasures);

        //when
        IngredientCommand ingredientCommand = converter.convert(command);

        //then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getId());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());

    }

    @Test
    public void convertWithNullUom(){
        //given
        Ingredient command=new Ingredient();
        command.setId(ID_VALUE);
        command.setAmount(AMMOUNT);
        command.setDescription(DESCRIPTION);


        //when
        IngredientCommand ingredientCommand = converter.convert(command);

        //then
        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMMOUNT, ingredientCommand.getAmount());

    }
}
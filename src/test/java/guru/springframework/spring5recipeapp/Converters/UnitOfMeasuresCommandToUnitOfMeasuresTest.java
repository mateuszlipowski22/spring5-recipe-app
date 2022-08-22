package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasuresCommandToUnitOfMeasuresTest {

    public static final String DESCRIPTION="description";
    public static final Long LONG_VALUE = 1L;

    UnitOfMeasuresCommandToUnitOfMeasures converter;

    @BeforeEach
    void setUp() {
        converter=new UnitOfMeasuresCommandToUnitOfMeasures();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasuresCommand()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasuresCommand command = new UnitOfMeasuresCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasures uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(DESCRIPTION, uom.getDescription());
        assertEquals(LONG_VALUE, uom.getId());

    }
}
package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasuresToUnitOfMeasuresCommandTest {

    public static final String DESCRIPTION="description";
    public static final Long LONG_VALUE = 1L;

    UnitOfMeasuresToUnitOfMeasuresCommand converter;

    @BeforeEach
    void setUp() {
        converter=new UnitOfMeasuresToUnitOfMeasuresCommand();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasures()));
    }
    @Test
    void convert() {
        //given
        UnitOfMeasures command = new UnitOfMeasures();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasuresCommand uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(DESCRIPTION, uom.getDescription());
        assertEquals(LONG_VALUE, uom.getId());
    }
}
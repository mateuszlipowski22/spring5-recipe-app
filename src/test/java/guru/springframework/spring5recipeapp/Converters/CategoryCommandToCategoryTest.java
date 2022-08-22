package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    private static final Long ID =1L;
    private static final String DESCRIPTION ="description";

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter=new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {

        //given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(command);

        //then
        assertNotNull(category);
        assertEquals(DESCRIPTION, category.getDescription());
        assertEquals(ID, category.getId());

    }
}
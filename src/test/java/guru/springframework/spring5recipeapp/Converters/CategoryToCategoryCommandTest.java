package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    private static final Long ID =1L;
    private static final String DESCRIPTION ="description";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter=new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {

        //given
        Category command = new Category();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //when
        CategoryCommand category = converter.convert(command);

        //then
        assertNotNull(category);
        assertEquals(DESCRIPTION, category.getDescription());
        assertEquals(ID, category.getId());

    }
}
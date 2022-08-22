package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    private static final Long ID =1L;
    private static final String RECIPE_NOTES ="recipe notes";

    NotesToNotesCommand converter;


    @BeforeEach
    void setUp() {
        converter=new NotesToNotesCommand();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {

        //given
        Notes command = new Notes();
        command.setId(ID);
        command.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
        assertEquals(ID, notes.getId());

    }
}
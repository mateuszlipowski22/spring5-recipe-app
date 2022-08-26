package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.Converters.UnitOfMeasuresToUnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasuresToUnitOfMeasuresCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasuresToUnitOfMeasuresCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() throws Exception {
        //given
        Set<UnitOfMeasures> unitOfMeasures = new HashSet<>();
        UnitOfMeasures uom1 = new UnitOfMeasures();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasures uom2 = new UnitOfMeasures();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasuresCommand> commands = service.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}
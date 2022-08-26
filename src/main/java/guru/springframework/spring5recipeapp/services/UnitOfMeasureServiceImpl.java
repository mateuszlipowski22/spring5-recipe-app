package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.Converters.UnitOfMeasuresToUnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasuresToUnitOfMeasuresCommand unitOfMeasuresToUnitOfMeasuresCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasuresToUnitOfMeasuresCommand unitOfMeasuresToUnitOfMeasuresCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasuresToUnitOfMeasuresCommand = unitOfMeasuresToUnitOfMeasuresCommand;
    }


    @Override
    public Set<UnitOfMeasuresCommand> listAllUoms() {
//        Set<UnitOfMeasures> unitOfMeasures = new HashSet<>();
//        unitOfMeasureRepository.findAll().iterator().forEachRemaining(unitOfMeasures::add);
//        return unitOfMeasures;
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(unitOfMeasuresToUnitOfMeasuresCommand::convert)
                .collect(Collectors.toSet());

    }
}

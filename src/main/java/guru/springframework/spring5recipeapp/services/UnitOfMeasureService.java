package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;

import java.util.List;
import java.util.Set;

public interface UnitOfMeasureService{

    Set<UnitOfMeasuresCommand> listAllUoms();

}

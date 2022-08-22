package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasuresToUnitOfMeasuresCommand implements Converter<UnitOfMeasures, UnitOfMeasuresCommand> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasuresCommand convert(UnitOfMeasures source) {
        if(source==null){
            return null;
        }
        final UnitOfMeasuresCommand uomc = new UnitOfMeasuresCommand();
        uomc.setId(source.getId());
        uomc.setDescription(source.getDescription());
        return uomc;
    }
}

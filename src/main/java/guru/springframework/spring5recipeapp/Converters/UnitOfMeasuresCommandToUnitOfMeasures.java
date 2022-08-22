package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasuresCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasuresCommandToUnitOfMeasures implements Converter<UnitOfMeasuresCommand, UnitOfMeasures> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasures convert(UnitOfMeasuresCommand source) {
        if(source==null){
            return null;
        }
        final UnitOfMeasures uom = new UnitOfMeasures();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }
}

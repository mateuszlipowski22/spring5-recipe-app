package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasuresToUnitOfMeasuresCommand uomcConverter;


    public IngredientToIngredientCommand(UnitOfMeasuresToUnitOfMeasuresCommand uomcConverter) {
        this.uomcConverter = uomcConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source==null){
            return null;
        }
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        ingredientCommand.setAmounts(source.getAmounts());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setUom(uomcConverter.convert(source.getUom()));

        return ingredientCommand;    }
}

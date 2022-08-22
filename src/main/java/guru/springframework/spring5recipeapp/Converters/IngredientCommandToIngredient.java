package guru.springframework.spring5recipeapp.Converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasuresCommandToUnitOfMeasures uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasuresCommandToUnitOfMeasures uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source==null){
            return null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmounts(source.getAmounts());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));

        return ingredient;
    }
}
package guru.springframework.spring5recipeapp.commands;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasures;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amounts;
    private UnitOfMeasuresCommand uom;

}

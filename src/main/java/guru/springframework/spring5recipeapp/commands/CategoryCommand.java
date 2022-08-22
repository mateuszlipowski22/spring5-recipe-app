package guru.springframework.spring5recipeapp.commands;

import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
}

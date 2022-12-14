package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UnitOfMeasures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}

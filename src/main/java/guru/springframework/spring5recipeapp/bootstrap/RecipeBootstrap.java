package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasures> eachUnitOfMeasuresOptional = unitOfMeasureRepository.findByDescription("Each");
        Optional<UnitOfMeasures> teaspoonUnitOfMeasuresOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasures> tablespoonUnitOfMeasuresOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasures> cupUnitOfMeasuresOptional = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasures> pinchUnitOfMeasuresOptional = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasures> ounceUnitOfMeasuresOptional = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasures> dashUnitOfMeasuresOptional = unitOfMeasureRepository.findByDescription("Dash");

        String[] listOfUnitOfMeasures = {"Each", "Teaspoon", "Tablespoon", "Cup", "Pinch", "Ounce", "Dash"};

        for (String listOfUnitOfMeasure : listOfUnitOfMeasures) {
            Optional<UnitOfMeasures> unitOfMeasuresOptional=unitOfMeasureRepository.findByDescription(listOfUnitOfMeasure);
            if(!unitOfMeasuresOptional.isPresent()){
                throw new RuntimeException("Expected UOM not found: "+ listOfUnitOfMeasure);
            }
        }

        UnitOfMeasures eachUom = eachUnitOfMeasuresOptional.get();
        UnitOfMeasures teaspoonUom = teaspoonUnitOfMeasuresOptional.get();
        UnitOfMeasures tablespoonUom = tablespoonUnitOfMeasuresOptional.get();
        UnitOfMeasures cupUom = cupUnitOfMeasuresOptional.get();
        UnitOfMeasures pinchUom = pinchUnitOfMeasuresOptional.get();
        UnitOfMeasures ounceUom = ounceUnitOfMeasuresOptional.get();
        UnitOfMeasures dashUom = dashUnitOfMeasuresOptional.get();


        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        String[] listOfUnitOfCategories = {"American", "Mexican"};

        for (String listOfUnitOfCategorie : listOfUnitOfCategories) {
            Optional<Category> categoryOptional=categoryRepository.findByDescription(listOfUnitOfCategorie);
            if(!categoryOptional.isPresent()){
                throw new RuntimeException("Expected UOM not found");
            }
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();


//        Yummy Guacamole
        Recipe guacaRecipe = new Recipe();
        guacaRecipe.setDescription("Perfect Guacamole");
        guacaRecipe.setCookTime(0);
        guacaRecipe.setPrepTime(10);
        guacaRecipe.setDifficulty(Difficulty.EASY);
        guacaRecipe.setDirections("\n" +
                "Cut the avocado:\n" +
                "\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "How to make guacamole - scoring avocado\n" +
                "Mash the avocado flesh:\n" +
                "\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "How to make guacamole - smashing avocado with fork\n" +
                "Add the remaining ingredients to taste:\n" +
                "\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "Serve immediately:\n" +
                "\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving. ");

        Notes guacaNotes = new Notes();
        guacaNotes.setRecipeNotes(" Guacamole Variations\n" +
                "\n" +
                "Once you have basic guacamole down, feel free to experiment with variations by adding strawberries, peaches, pineapple, mangoes, or even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with your homemade guacamole!\n" +
                "\n" +
                "    Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of other ingredients stop you from making guacamole.\n" +
                "    Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "    Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.\n");
        guacaRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacaRecipe.setNotes(guacaNotes);

        guacaRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacaRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom));
        guacaRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom));
        guacaRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacaRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacaRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacaRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacaRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacaRecipe.getCategories().add(americanCategory);
        guacaRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacaRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pinchUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }

}

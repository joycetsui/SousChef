package com.workingtitle.makeit;

import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by xiwen on 23/7/16.
 */
public class FilterSearch {

    static ArrayList<String> vegan = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "blue cheese", "butter", "cheddar cheese", "cheese", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "clams", "cod", "cooked chicken", "cottage cheese", "crab", "crab meat", "cream", "cream cheese", "cream of mushroom soup", "cream of tartar", "creme", "creme fraiche", "eggnog", "egg noodles", "egg", "evaporated milk", "fish sauce", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "haddock", "halibut", "ham", "heavy cream", "hotdogs", "kahula", "marshmallows", "mayonnaise", "meat", "milk", "milk powder", "monterey jack cheese", "mozzarella cheese", "mussels", "oysters", "parmesan cheese", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "ricotta cheese", "romano cheese", "salmon", "scallops", "shrimp", "sirloin", "sirloin beef", "steak", "tilapia", "tuna", "turkey", "venison", "whipped topping", "whipping cream", "whole chicken", "worcestershire sauce",
            "yogurt"));

    static ArrayList<String> vegetarian = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "clams", "cod", "cooked chicken", "crab", "crab meat", "fish sauce", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "haddock", "halibut", "ham", "hotdogs", "meat", "mussels", "oysters", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "salmon", "scallops", "shrimp", "sirloin", "sirloin beef", "steak", "tilapia", "tuna", "turkey", "venison", "whole chicken",
            "worcestershire sauce"));

    static ArrayList<String> pescatarian = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "cooked chicken", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "ham", "hotdogs", "meat", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "sirloin", "sirloin beef", "steak", "turkey", "venison", "whole chicken",
            "worcestershire sauce"));

    static ArrayList<String> nuts = new ArrayList<String> (Arrays.asList("hazelnuts", "macadamia nuts", "nuts", "peanuts", "pine nuts", "walnuts", "peanut butter", "peanut butter cups", "peanut oil", "water chestnut", "pistachio", "walnuts", "almond butter", "almond extract", "almond meal", "almond milk", "almond paste", "almonds",
            "cashews"));

    static ArrayList<String> shellfish = new ArrayList<String> (Arrays.asList("shrimp", "crab", "crab meat", "prawns", "clams", "mussels", "oysters",
            "scallops"));

    static ArrayList<String> lactose = new ArrayList<String> (Arrays.asList("condensed milk", "evaporated milk", "milk", "milk powder", "cream", "cream cheese", "heavy cream", "ice cream", "whipping cream", "cottage cheese", "feta cheese", "monterey jack cheese", "mozzarella cheese", "parmesan cheese", "ricotta cheese", "romano cheese", "swiss cheese", "pudding", "butter", "buttermilk", "sour cream", "blue cheese", "cheddar cheese", "cheese", "cream of mushroom soup", "yogurt",
            "margarine"));

    public static RecipeCollection filterByVegan(RecipeCollection collection){
        boolean found = false;
        Recipe recipe;
        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);
            String ingredients = recipe.getIngredients();
            found = false;
            for (String ingr : vegan){
                found = ingredients.contains(ingr);
                if (found){
                    collection.removeRecipe(i);
                    break;
                }
            }
            if (!found) {
                i++;
            }
        }

        return collection;
    }

    public static RecipeCollection filterByVegetarian(RecipeCollection collection){
        boolean found = false;
        Recipe recipe;
        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);
            String ingredients = recipe.getIngredients();
            found = false;
            for (String ingr : vegetarian){
                found = ingredients.contains(ingr);
                if (found){
                    collection.removeRecipe(i);
                    break;
                }
            }
            if (!found) {
                i++;
            }
        }

        return collection;
    }

    public static RecipeCollection filterByPescatarian(RecipeCollection collection){
        boolean found = false;
        Recipe recipe;
        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);
            String ingredients = recipe.getIngredients();
            found = false;
            for (String ingr : pescatarian){
                found = ingredients.contains(ingr);
                if (found){
                    collection.removeRecipe(i);
                    break;
                }
            }
            if (!found) {
                i++;
            }
        }

        return collection;
    }

    public static RecipeCollection filterByNuts(RecipeCollection collection){
        boolean found = false;
        Recipe recipe;
        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);
            String ingredients = recipe.getIngredients();
            found = false;
            for (String ingr : nuts){
                found = ingredients.contains(ingr);
                if (found){
                    collection.removeRecipe(i);
                    break;
                }
            }
            if (!found) {
                i++;
            }
        }

        return collection;
    }

    public static RecipeCollection filterByShellfish(RecipeCollection collection){
        boolean found = false;
        Recipe recipe;
        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);
            String ingredients = recipe.getIngredients();
            found = false;
            for (String ingr : shellfish){
                found = ingredients.contains(ingr);
                if (found){
                    collection.removeRecipe(i);
                    break;
                }
            }
            if (!found) {
                i++;
            }
        }

        return collection;
    }

    public static RecipeCollection filterByLactose(RecipeCollection collection){
        boolean found = false;
        Recipe recipe;
        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);
            String ingredients = recipe.getIngredients();
            found = false;
            for (String ingr : lactose){
                found = ingredients.contains(ingr);
                if (found){
                    collection.removeRecipe(i);
                    break;
                }
            }
            if (!found) {
                i++;
            }
        }

        return collection;
    }
}

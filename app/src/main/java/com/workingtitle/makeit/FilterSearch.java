package com.workingtitle.makeit;

import com.workingtitle.makeit.models.Recipe;
import com.workingtitle.makeit.models.RecipeCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiwen on 23/7/16.
 */
public class FilterSearch {

    // Search Options info
    public static int cookTimeHour = -1;
    public static int cookTimeMinute = -1;
    public static int numPortions = -1;


    // User Settings
    static ArrayList<String> vegan = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "blue cheese", "butter", "cheddar cheese", "cheese", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "clams", "cod", "cooked chicken", "cottage cheese", "crab", "crab meat", "cream", "cream cheese", "cream of mushroom soup", "cream of tartar", "creme", "creme fraiche", "eggnog", "egg noodles", "egg", "evaporated milk", "fish sauce", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "haddock", "halibut", "ham", "heavy cream", "hotdogs", "kahula", "marshmallows", "mayonnaise", "meat", "milk", "milk powder", "monterey jack cheese", "mozzarella cheese", "mussels", "oysters", "parmesan cheese", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "ricotta cheese", "romano cheese", "salmon", "scallops", "shrimp", "sirloin", "sirloin beef", "steak", "tilapia", "tuna", "turkey", "venison", "whipped topping", "whipping cream", "whole chicken", "worcestershire sauce",
            "yogurt"));

    static ArrayList<String> vegetarian = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "clams", "cod", "cooked chicken", "crab", "crab meat", "fish sauce", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "haddock", "halibut", "ham ","ham,", "hotdogs", "meat", "mussels", "oysters", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "salmon", "scallops", "shrimp", "sirloin", "sirloin beef", "steak", "tilapia", "tuna", "turkey", "venison", "whole chicken",
            "worcestershire sauce"));

    static ArrayList<String> pescatarian = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "cooked chicken", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "ham ", "ham,", "hotdogs", "meat", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "sirloin", "sirloin beef", "steak", "turkey", "venison", "whole chicken",
            "worcestershire sauce"));

    static ArrayList<String> nuts = new ArrayList<String> (Arrays.asList("hazelnuts", "macadamia nuts", "nuts", "peanuts", "pine nuts", "walnuts", "peanut butter", "peanut butter cups", "peanut oil", "water chestnut", "pistachio", "walnuts", "almond butter", "almond extract", "almond meal", "almond milk", "almond paste", "almonds",
            "cashews"));

    static ArrayList<String> shellfish = new ArrayList<String> (Arrays.asList("shrimp", "crab", "crab meat", "prawns", "clams", "mussels", "oysters",
            "scallops"));

    static ArrayList<String> lactose = new ArrayList<String> (Arrays.asList("condensed milk", "evaporated milk", "milk", "milk powder", "cream", "cream cheese", "heavy cream", "ice cream", "whipping cream", "cottage cheese", "feta cheese", "monterey jack cheese", "mozzarella cheese", "parmesan cheese", "ricotta cheese", "romano cheese", "swiss cheese", "pudding", "butter", "buttermilk", "sour cream", "blue cheese", "cheddar cheese", "cheese", "cream of mushroom soup", "yogurt",
            "margarine"));


    public static void filterByVegan(RecipeCollection collection){
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
    }

    public static void filterByVegetarian(RecipeCollection collection){
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
    }

    public static void filterByPescatarian(RecipeCollection collection){
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
    }

    public static void filterByNuts(RecipeCollection collection){
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
    }

    public static void filterByShellfish(RecipeCollection collection){
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
    }

    public static void filterByLactose(RecipeCollection collection){
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
    }

    public static void filterByNumberOfPortions(RecipeCollection collection){
        Recipe recipe;
        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);
            int servingSize = recipe.getServingSize();
            if (servingSize < numPortions){
                collection.removeRecipe(i);
            }
            else{
                i++;
            }
        }
    }

    public static void filterByCookTime(RecipeCollection collection){
        Recipe recipe;

        int filterTotalTime = getFilterCookTime();

        for (int i = 0; i < collection.getRecipeCollection().size(); ){
            recipe = collection.getRecipeCollection().get(i);

            String cookTime = recipe.getCookTime();
            int recipeTotalTime = parseCookTime(cookTime);

            if (filterTotalTime < recipeTotalTime){
                collection.removeRecipe(i);
            }
            else{
                i++;
            }
        }
    }

    private static int parseCookTime(String cookTime){
        String patternHour = "(\\s*)((\\d+)(\\s*)(h|hour))(\\s*)";
        String patternMinute = "(\\s*)((\\d+)(\\s*)(m|minute))(\\s*)";
//        String patternSeconds = "(\\s*)((\\d+)(\\s*)s)(\\s*)";

        // Create a Pattern object
        Pattern rHour = Pattern.compile(patternHour);
        Pattern rMin = Pattern.compile(patternMinute);

        Matcher matcher;


        String hours = "";
        String minutes = "";

        // Now create matcher object.
        matcher = rHour.matcher(cookTime);
        if (matcher.find( )) {
            hours =  matcher.group(0);
        }

        matcher = rMin.matcher(cookTime);
        if (matcher.find( )) {
            minutes =  matcher.group(0);
        }

        hours = hours.replaceAll("[^\\d.]", "");
        hours = hours.trim();
        int h = 0;
        if (hours != "") {
            h = Integer.parseInt(hours);
        }

        minutes = minutes.replaceAll("[^\\d.]", "");
        minutes = minutes.trim();
        int m = 0;
        if (minutes != "") {
            m = Integer.parseInt(minutes);
        }

        int recipeTotalTime = m + h*60;

        return recipeTotalTime;
    }

    private static int getFilterCookTime(){
        int filterTotalTime = 0;

        if (cookTimeHour != -1){
            filterTotalTime += cookTimeHour*60;
        }
        if (cookTimeMinute != -1){
            filterTotalTime += cookTimeMinute;
        }

        return filterTotalTime;
    }
}

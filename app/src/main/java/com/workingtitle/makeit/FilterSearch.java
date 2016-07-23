package com.workingtitle.makeit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by xiwen on 23/7/16.
 */
public class FilterSearch {
    ArrayList<String> vegan;
    ArrayList<String> vegetarian;
    ArrayList<String> pescetarian;

    ArrayList<String> nuts;
    ArrayList<String> shellfish;
    ArrayList<String> lactose;

    public void initialize(){

        vegan = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "blue cheese", "butter", "cheddar cheese", "cheese", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "clams", "cod", "cooked chicken", "cottage cheese", "crab", "crab meat", "cream", "cream cheese", "cream of mushroom soup", "cream of tartar", "creme", "creme fraiche", "eggnog", "egg noodles", "eggs", "evaporated milk", "fish sauce", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "haddock", "halibut", "ham", "heavy cream", "hotdogs", "kahula", "marshmallows", "mayonnaise", "meat", "milk", "milk powder", "monterey jack cheese", "mozzarella cheese", "mussels", "oysters", "parmesan cheese", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "ricotta cheese", "romano cheese", "salmon", "scallops", "shrimp", "sirloin", "sirloin beef", "steak", "tilapia", "tuna", "turkey", "venison", "whipped topping", "whipping cream", "whole chicken", "worcestershire sauce",
                                        "yogurt"));

        vegetarian = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "clams", "cod", "cooked chicken", "crab", "crab meat", "fish sauce", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "haddock", "halibut", "ham", "hotdogs", "meat", "mussels", "oysters", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "salmon", "scallops", "shrimp", "sirloin", "sirloin beef", "steak", "tilapia", "tuna", "turkey", "venison", "whole chicken",
                                        "worcestershire sauce"));

        pescetarian = new ArrayList<String> (Arrays.asList("bacon", "beef", "beef bouillon", "beef brisket", "beef broth", "beef chuck", "beef sirloin", "beef stock", "beef tenderloin", "bef consomme", "chicken", "chicken bouillon", "chicken breast", "chicken broth", "chicken drumsticks", "chicken stock", "chicken tenders", "chicken thighs", "chicken wings", "cooked chicken", "ground beef", "ground chicken", "ground lamb", "ground pork", "ground turkey", "ground veal", "ham", "hotdogs", "meat", "pepperoni", "pork", "pork chops", "pork sausage", "pork tenderloin", "prosciutto", "sirloin", "sirloin beef", "steak", "turkey", "venison", "whole chicken",
                                        "worcestershire sauce"));


        nuts = new ArrayList<String> (Arrays.asList("hazelnuts", "macadamia nuts", "nuts", "peanuts", "pine nuts", "walnuts", "peanut butter", "peanut butter cups", "peanut oil", "water chestnut", "pistachio", "walnuts", "almond butter", "almond extract", "almond meal", "almond milk", "almond paste", "almonds",
                                        "cashews"));

        shellfish = new ArrayList<String> (Arrays.asList("shrimp", "crab", "crab meat", "prawns", "clams", "mussels", "oysters",
                                        "scallops"));

        lactose = new ArrayList<String> (Arrays.asList("condensed milk", "evaporated milk", "milk", "milk powder", "cream", "cream cheese", "heavy cream", "ice cream", "whipping cream", "cottage cheese", "feta cheese", "monterey jack cheese", "mozzarella cheese", "parmesan cheese", "ricotta cheese", "romano cheese", "swiss cheese", "pudding", "butter", "buttermilk", "sour cream", "blue cheese", "cheddar cheese", "cheese", "cream of mushroom soup", "yogurt",
                                        "margarine"));
    }


}

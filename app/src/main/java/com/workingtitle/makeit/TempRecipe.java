package com.workingtitle.makeit;

/**
 * Created by Maricarla on 2016-06-20.
 */
public class TempRecipe {
    int id;
    String title;
    String author;
    Double rating;
    Double ratingScale;
    int reviewCount;
    int cookTime;
    int servingSize;
    String directions;
    String ingredients;

    TempRecipe(int id, String title, String author, Double rating, Double ratingScale, int reviewCount, int cookTime, int servingSize, String directions, String ingredients){
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.ratingScale = ratingScale;
        this.reviewCount = reviewCount;
        this.cookTime = cookTime;
        this.servingSize = servingSize;
        this.directions = directions;
        this.ingredients = ingredients;
    }
}

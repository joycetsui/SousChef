package com.workingtitle.makeit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Joyce on 6/17/2016.
 */
public class Recipe implements Serializable {
    String id;
    @SerializedName("recipe_id")
    String recipeId;
    String title;
    String author;
    float rating;
    @SerializedName("rating_scale")
    String ratingScale;
    @SerializedName("review_count")
    String reviewCount;
    @SerializedName("cook_time")
    String cookTime;
    @SerializedName("serving_size")
    String servingSize;
    String directions;
    String ingredients;

    public String getId() {
        return id;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public float getRating() {
        return rating;
    }

    public String getRatingScale() {
        return ratingScale;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public String getCookTime() {
        return cookTime;
    }

    public String getServingSize() {
        return servingSize;
    }

    public String getDirections() {
        return directions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setRatingScale(String ratingScale) {
        this.ratingScale = ratingScale;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}

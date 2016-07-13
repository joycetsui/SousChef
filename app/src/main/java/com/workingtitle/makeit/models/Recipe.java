package com.workingtitle.makeit.models;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Joyce on 6/17/2016.
 */
public class Recipe implements Serializable {
    private Integer id;
    @SerializedName("recipe_id")
    private Integer recipeId;
    private String title;
    private String author;
    private float rating;
    @SerializedName("rating_scale")
    private Integer ratingScale;
    @SerializedName("review_count")
    private Integer reviewCount;
    @SerializedName("cook_time")
    private String cookTime;
    @SerializedName("serving_size")
    private Integer servingSize;
    private String directions;
    private String ingredients;

    public Integer getId() {
        return id;
    }

    public Integer getRecipeId() {
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

    public Integer getRatingScale() {
        return ratingScale;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public String getCookTime() {
        return cookTime;
    }

    public Integer getServingSize() {
        return servingSize;
    }

    public String getDirections() {
        return directions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRecipeId(Integer recipeId) {
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

    public void setRatingScale(Integer ratingScale) {
        this.ratingScale = ratingScale;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public void setServingSize(Integer servingSize) {
        this.servingSize = servingSize;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe loadData(String JSONObject) {
        JsonElement element = new JsonParser().parse(JSONObject);

        // full API Response body
        JsonObject object = element.getAsJsonObject();

        // lets get the nested JSON object inside data key which contains a recipe and populate the fields
        object = object.getAsJsonObject("data");
        return populate(object);
    }

    public Recipe populate(JsonObject object) {

        this.setId(Integer.parseInt(object.getAsJsonObject().getAsJsonPrimitive("id").toString()));
        this.setRecipeId(Integer.parseInt(object.getAsJsonObject().getAsJsonPrimitive("recipe_id").toString()));
        this.setTitle(object.getAsJsonObject().getAsJsonPrimitive("title").toString());
        this.setAuthor(object.getAsJsonObject().getAsJsonPrimitive("author").toString());
        this.setRating(Float.parseFloat(object.getAsJsonObject().getAsJsonPrimitive("recipe_id").toString()));
        this.setRatingScale(Integer.parseInt(object.getAsJsonObject().getAsJsonPrimitive("rating_scale").toString()));
        this.setReviewCount(Integer.parseInt(object.getAsJsonObject().getAsJsonPrimitive("review_count").toString()));
        this.setCookTime(object.getAsJsonObject().getAsJsonPrimitive("cook_time").toString());
        this.setServingSize(Integer.parseInt(object.getAsJsonObject().getAsJsonPrimitive("serving_size").toString()));
        this.setDirections(object.getAsJsonObject().getAsJsonPrimitive("directions").toString());
        this.setIngredients(object.getAsJsonObject().getAsJsonPrimitive("ingredients").toString());
        String temp =  this.getDirections();
        
        return this;

    }
}

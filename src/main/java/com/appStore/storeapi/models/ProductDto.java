package com.appStore.storeapi.models;


import jakarta.validation.constraints.*;


public class ProductDto {

    @NotEmpty(message="The name is required")
    private String name;

    @NotEmpty(message="The brand is required")
    private String brand;

    @NotEmpty(message="The category is required")
    private String category;

    @NotEmpty(message="The image is required")
    private String imageUrl;

     @Min(value = 0,message = "The price is required")
    //@NotEmpty(message="The price is required")
    private String price;

    @NotEmpty(message="The description is required")
    @Size(min = 10, message = "the description should be at least 10 characters")
    @Size(max=2000,message="the description can not exceed 2000 characters")
    private String description;

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice (String price) {
        this.price = price;
    }

    public  String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotEmpty(message = "The image is required") String getImageUrl() {return imageUrl;}

    public void setImageUrl(@NotEmpty(message = "The image is required") String imageUrl) {this.imageUrl = imageUrl;}

}

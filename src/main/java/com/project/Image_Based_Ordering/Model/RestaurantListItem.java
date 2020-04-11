package com.project.Image_Based_Ordering.Model;

public class RestaurantListItem {
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "RestaurantList{" +
                "restaurant=" + restaurant +
                '}';
    }

    Restaurant restaurant;
}

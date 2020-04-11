package com.project.Image_Based_Ordering.Model;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Iterator;

public class Search {


    ArrayList<RestaurantListItem> restaurants;

    public ArrayList<RestaurantListItem> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<RestaurantListItem> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Restaurant> getRestaurantsObjects()
    {
        ArrayList<Restaurant> RestaturantObjects= new ArrayList<>();
        try {
            if(restaurants== null)
            {
                throw new NullPointerException("Not Intialized yet");

            }
            else
            {
                Iterator<RestaurantListItem> iter=restaurants.iterator();
                while (iter.hasNext())
                {
                    RestaturantObjects.add(iter.next().getRestaurant());
                }

            }

        }
        catch (NullPointerException ne)
        {
            ne.printStackTrace();
        }
        return RestaturantObjects;
    }
    @Override
    public String toString() {
        return "Search{" +
                "restaurants=" + restaurants +
                '}';
    }
}

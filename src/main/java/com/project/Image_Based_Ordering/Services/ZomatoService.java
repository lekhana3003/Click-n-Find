package com.project.Image_Based_Ordering.Services;

import com.project.Image_Based_Ordering.Model.Predictions;
import com.project.Image_Based_Ordering.Model.Restaurant;
import com.project.Image_Based_Ordering.Model.ZomatoRequest;

import java.util.ArrayList;

public interface ZomatoService {
    public ArrayList<Restaurant> fetchRestaurants(ZomatoRequest zomatoRequest);
    public ArrayList<Restaurant> callZomatoAPI(String foodName,String entity_type,Integer entity_id);
    public ArrayList<Restaurant> callZomatoAPI(String foodName,Double lat,Double lon);
}

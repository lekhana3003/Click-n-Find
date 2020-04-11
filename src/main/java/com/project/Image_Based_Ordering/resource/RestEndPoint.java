package com.project.Image_Based_Ordering.resource;


import com.project.Image_Based_Ordering.Model.*;
import com.project.Image_Based_Ordering.Services.Impl.PredictionModel;
import com.project.Image_Based_Ordering.Services.Impl.ZomatoModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
public class RestEndPoint {
ArrayList<Predictions> predictions;

    @PostMapping("/getRestaurants")
    public String all(@RequestParam("file") MultipartFile image){

        PredictionModel predictionModel=new PredictionModel();
        ZomatoModel zomatoModel=new ZomatoModel();
        predictions=predictionModel.predict(image);
        ArrayList<Restaurant> restaurants=zomatoModel.fetchRestaurants(predictions);

     return restaurants.toString();

    }


}

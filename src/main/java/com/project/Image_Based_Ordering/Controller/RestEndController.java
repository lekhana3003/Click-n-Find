package com.project.Image_Based_Ordering.Controller;


import com.project.Image_Based_Ordering.Model.*;
import com.project.Image_Based_Ordering.Services.Impl.PredictionServiceImpl;
import com.project.Image_Based_Ordering.Services.Impl.ZomatoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController

public class RestEndController {
    ArrayList<Predictions> predictions;

    @Value("${Zomato.Entity_id}")
    String entity_id;

    @Value("${Zomato.Entity_type}")
    String entity_type;

    @Value("${PredictionKey}")
    String predictionKey;

    @Autowired
    PredictionServiceImpl predictionServiceImpl;

    @Autowired
    ZomatoServiceImpl zomatoServiceImpl;


    @PostMapping("/getRestaurants")
    public String all(@RequestParam("image") MultipartFile image,@RequestParam("lat") Double lat,@RequestParam("lon") Double lon) throws IOException {
        predictions=predictionServiceImpl.predict(image.getBytes());
        ArrayList<Restaurant> restaurants=zomatoServiceImpl.fetchRestaurants(new ZomatoRequest(predictions,lat,lon));
     return restaurants.toString();

    }


}

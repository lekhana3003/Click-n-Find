package com.project.Image_Based_Ordering.Services.Impl;

import com.project.Image_Based_Ordering.Model.Predictions;
import com.project.Image_Based_Ordering.Model.Restaurant;
import com.project.Image_Based_Ordering.Model.Search;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ZomatoModel {
    public ArrayList<Restaurant> fetchRestaurants(ArrayList<Predictions> predictions)
    {
        Integer integer=0;
        HashSet<Restaurant> uniqueRestaurants =new HashSet<>();
        Iterator<Predictions> predictionsIterator=predictions.iterator();
        while(predictionsIterator.hasNext())
        {
            integer++;
            System.out.println(predictions);
            if(integer<3) {
                ArrayList<Restaurant> restaurantsList = callZomatoAPI(predictionsIterator.next().getName());
                Iterator<Restaurant> restaurantIterator=restaurantsList.iterator();
               while (restaurantIterator.hasNext()) {

                    uniqueRestaurants.add(restaurantIterator.next());
                    //System.out.println(restaurantIterator.next());
                }
            }
            else
            {
                break;
            }
        }
        ArrayList<Restaurant> results=new ArrayList<>(uniqueRestaurants);
        return results;
    }

    public ArrayList<Restaurant> callZomatoAPI(String foodName)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", "5b795ce23dbf1238866b09dc60915687");
        HttpEntity entity = new HttpEntity(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://developers.zomato.com/api/v2.1/search")
                .queryParam("entity_id", "5008")
                .queryParam("entity_type","subzone")
                .queryParam("q",foodName);

        HttpEntity<Search> resp = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Search.class);

        return resp.getBody().getRestaurantsObjects();

    }
}

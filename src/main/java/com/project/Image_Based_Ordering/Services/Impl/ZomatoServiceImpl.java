package com.project.Image_Based_Ordering.Services.Impl;

import com.project.Image_Based_Ordering.Model.Predictions;
import com.project.Image_Based_Ordering.Model.Restaurant;
import com.project.Image_Based_Ordering.Model.Search;
import com.project.Image_Based_Ordering.Model.ZomatoRequest;
import com.project.Image_Based_Ordering.Services.ZomatoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.util.*;
@Service
public class ZomatoServiceImpl implements ZomatoService {
@Value("${Zomato.URL}")
String ZomatoURL;

@Value("${Zomato.Key}")
String ZomatoKey;

@Value("${PredictionsLimit}")
String predictionsLimit;
    @Override
    public ArrayList<Restaurant> fetchRestaurants(ZomatoRequest zomatoRequest)
    {
        Integer integer=0;
        HashSet<Restaurant> uniqueRestaurants =new HashSet<>();
        ArrayList<Restaurant> restaurantsList;
        Iterator<Predictions> predictionsIterator=zomatoRequest.getPredictions().iterator();
        while(predictionsIterator.hasNext())
        {
            if(integer<Integer.parseInt(predictionsLimit)) {
                if(zomatoRequest.getLocationType()=="ENTITY") {
                restaurantsList = callZomatoAPI(predictionsIterator.next().getName(), zomatoRequest.getEntity_id(), zomatoRequest.getEntity_type());
                }
                else
                {
                    //Change when laitude and longitude are implemented
                   restaurantsList = callZomatoAPI(predictionsIterator.next().getName(), zomatoRequest.getEntity_id(), zomatoRequest.getEntity_type());
                }
                Iterator<Restaurant> restaurantIterator=restaurantsList.iterator();
               while (restaurantIterator.hasNext()) {

                    uniqueRestaurants.add(restaurantIterator.next());
                }
            }
            else
            {
                break;
            }
            integer++;
        }
        ArrayList<Restaurant> results=new ArrayList<>(uniqueRestaurants);
        return results;
    }
    @Override
    public ArrayList<Restaurant> callZomatoAPI(String foodName,String entity_type,Integer entity_id)
    {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", ZomatoKey);
        HttpEntity entity = new HttpEntity(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ZomatoURL)
                .queryParam("entity_id", entity_id)
                .queryParam("entity_type",entity_type)
                .queryParam("q",foodName);

        HttpEntity<Search> resp = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Search.class);

        return resp.getBody().getRestaurantsObjects();

    }
}

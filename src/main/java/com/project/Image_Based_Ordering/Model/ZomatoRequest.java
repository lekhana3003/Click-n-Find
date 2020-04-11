package com.project.Image_Based_Ordering.Model;

import java.util.ArrayList;

public class ZomatoRequest {


    ArrayList<Predictions> predictions;
    String entity_id;
    Integer entity_type;
    Double lat;
    Double lon;
    String locationType;
    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }


    public ZomatoRequest(ArrayList<Predictions> predictions, String entity_id, Integer entity_type) {
        this.predictions = predictions;
        this.entity_id = entity_id;
        this.entity_type = entity_type;
        this.locationType = "ENTITY";
    }

    public ZomatoRequest(ArrayList<Predictions> predictions, Double lat, Double lon) {
        this.predictions = predictions;
        this.lat = lat;
        this.lon = lon;
        this.locationType = "LATLON";
    }

    public ArrayList<Predictions> getPredictions() {
        return predictions;
    }

    public void setPredictions(ArrayList<Predictions> predictions) {
        this.predictions = predictions;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public Integer getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(Integer entity_type) {
        this.entity_type = entity_type;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
    @Override
    public String toString() {
        return "ZomatoRequest{" +
                "predictions=" + predictions +
                ", entity_id='" + entity_id + '\'' +
                ", entity_type=" + entity_type +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

}

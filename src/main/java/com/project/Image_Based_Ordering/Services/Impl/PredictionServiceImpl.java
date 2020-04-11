package com.project.Image_Based_Ordering.Services.Impl;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import com.project.Image_Based_Ordering.Model.Predictions;
import com.project.Image_Based_Ordering.Services.PredictionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    @Value("${PredictionKey}")
    String predictionKey;
    @Override
    public ArrayList<Predictions> predict(byte[] image)
    {
        List<ClarifaiOutput<Concept>> response =null;
        ClarifaiClient client = new ClarifaiBuilder(predictionKey)
                .buildSync();

    try {
               response= client.getDefaultModels().foodModel().predict()
                        .withInputs(
                                ClarifaiInput.forImage(ClarifaiImage.of(image))
                        )
                        .executeSync()
                        .get();


    }
    catch (Exception e)
    {
        e.printStackTrace();
    }

    return addPredictions(response.get(0).data());
    }

    @Override
    public ArrayList<Predictions> addPredictions(List<Concept> response) {
        ArrayList<Predictions> predictions=new ArrayList<>();
        response.forEach((Concept c)->{
                    predictions.add(new Predictions(c.name(),c.value()));
                });
        return predictions;
    }

}

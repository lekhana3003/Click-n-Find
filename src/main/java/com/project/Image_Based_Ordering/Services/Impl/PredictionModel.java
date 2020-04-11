package com.project.Image_Based_Ordering.Services.Impl;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import com.project.Image_Based_Ordering.Model.Predictions;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PredictionModel {

ArrayList<Predictions> predictions=new ArrayList<>();
    public ArrayList<Predictions> predict(byte[] image)
    {
        ClarifaiClient client = new ClarifaiBuilder("bdc56a7d919f4824ad45c168c33f0f42")
                .buildSync();

    try {
        final List<ClarifaiOutput<Concept>> response =
                client.getDefaultModels().foodModel().predict()
                        .withInputs(
                                ClarifaiInput.forImage(ClarifaiImage.of(image))
                        )
                        .executeSync()
                        .get();
        response.get(0)
                .data()
                .forEach((Concept c)->{


            predictions.add(new Predictions(c.name(),c.value()));
        });
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    return predictions;
    }

}

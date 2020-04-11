package com.project.Image_Based_Ordering.Services;

import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import com.project.Image_Based_Ordering.Model.Predictions;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface PredictionService {
    public ArrayList<Predictions> predict(byte[] image);
    public ArrayList<Predictions> addPredictions(List<Concept> response);
}

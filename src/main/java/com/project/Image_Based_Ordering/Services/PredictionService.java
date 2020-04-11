package com.project.Image_Based_Ordering.Services;

import com.project.Image_Based_Ordering.Model.Predictions;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface PredictionService {
    public ArrayList<Predictions> predict(byte[] image);
}

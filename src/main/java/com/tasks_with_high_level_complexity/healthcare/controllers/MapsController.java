package com.tasks_with_high_level_complexity.healthcare.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TravelMode;

@RestController
@RequestMapping("/maps")
public class MapsController {

    @Value("${MAPS_API_KEY}")
    private String mapsApiKey;

    @Value("${ORIGIN_COORDINATES}")
    private String originCoordinates;

    @Value("${DESTINATION_ADDRESS}")
    private String destinationAddress;

    @GetMapping("/directions")
    public List<String> getDirectionsToHealthcareFacility() {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(mapsApiKey)
                .build();

        DirectionsApiRequest request = DirectionsApi.newRequest(context)
                .origin(originCoordinates)
                .destination(destinationAddress)
                .mode(TravelMode.DRIVING);

        try {
            DirectionsResult result = request.await();

            // Extract and process the steps from the first leg of the first route
            List<String> directions = new ArrayList<>();
            for (DirectionsStep step : result.routes[0].legs[0].steps) {
                directions.add(step.htmlInstructions);
            }

            // Return the list of step-by-step directions
            return directions;
        } catch (Exception e) {
            // Handle any exceptions that might occur during the API request
            return Collections.singletonList("Error getting directions");
        }
    }
}

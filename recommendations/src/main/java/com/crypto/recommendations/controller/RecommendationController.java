package com.crypto.recommendations.controller;

import com.crypto.recommendations.response.CryptoNormalizedRangeResponse;
import com.crypto.recommendations.response.CryptoStatisticsResponse;
import com.crypto.recommendations.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/statistics/")
    public CryptoStatisticsResponse getStatistics() {
        return recommendationService.getStatistics();
    }

    @GetMapping("/statistics/{cryptoName}")
    public CryptoStatisticsResponse getStatistics(@PathVariable String cryptoName) {
        return recommendationService.getStatistics(cryptoName);
    }

    @GetMapping("/normalized-range/")
    public CryptoNormalizedRangeResponse normalizedRange() {
        return recommendationService.normalizedRange();
    }

    @GetMapping("/normalized-range/{date}")
    public CryptoNormalizedRangeResponse normalizedRange(@PathVariable String date) {

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            CryptoNormalizedRangeResponse response = new CryptoNormalizedRangeResponse();
            response.setErrorMessage("Wrong date parameter!");
            return response;
        }

        return recommendationService.normalizedRange(localDate);
    }

    @GetMapping("/**")
    public String error() {
        return "This URL does not exist";
    }
}

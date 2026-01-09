package com.quickmart.controller;

import com.quickmart.payload.AnalyticsResponse;
import com.quickmart.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/admin/app/analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics() {
        AnalyticsResponse reponse = analyticsService.getAnalyticsData();
        return new ResponseEntity<AnalyticsResponse>(reponse, HttpStatus.OK);
    }
}

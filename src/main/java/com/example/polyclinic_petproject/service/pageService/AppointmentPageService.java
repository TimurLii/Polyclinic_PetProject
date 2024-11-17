package com.example.polyclinic_petproject.service.pageService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AppointmentPageService {
    private RestClient restClient;

    public AppointmentPageService() {
        this.restClient = RestClient.create("http://localhost:8080");
    }
}

package com.scb.creditcardapplication.controller;

import com.scb.creditcardapplication.model.Application;
import com.scb.creditcardapplication.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    // ✅ POST: Save application + documents
    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationRepository.save(application);
    }

    // ✅ GET: fetch all applications
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // ✅ GET: fetch application by ID
    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }
}

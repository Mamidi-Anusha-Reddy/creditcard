package com.scb.creditcardapplication.controller;

import com.scb.creditcardapplication.model.Application;
import com.scb.creditcardapplication.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    // POST
    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationRepository.save(application);
    }

    // GET all
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }
}

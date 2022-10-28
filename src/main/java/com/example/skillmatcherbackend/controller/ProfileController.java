package com.example.skillmatcherbackend.controller;

import com.example.skillmatcherbackend.exception.DefaultException;
import com.example.skillmatcherbackend.model.IdResponse;
import com.example.skillmatcherbackend.model.dto.ProfileDTO;
import com.example.skillmatcherbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/profile")
public class ProfileController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllProfiles() {
        return new ResponseEntity<>(userService.getProfiles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable final String id) throws DefaultException {
        return new ResponseEntity<>(userService.getProfileById(id), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateProfile(@RequestBody final ProfileDTO profileDTO) throws DefaultException {
        return new ResponseEntity<>(new IdResponse(userService.updateProfile(profileDTO).getId()), HttpStatus.OK);
    }
}
package com.example.skillmatcherbackend.model.dto;

import com.example.skillmatcherbackend.model.UserWorkPreference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private String id;
    private String photo;
    private List<String> skills;
    private UserWorkPreference workPreference;
    private String resume;
    private String city;
    private String country;
    private Boolean openToTravel;
    private String phoneNumber;
}
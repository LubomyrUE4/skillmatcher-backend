package com.example.skillmatcherbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String id;
    private String userName;
    private String email;
    private UserRole role;
    private String photo;
    private List<String> skills;
    private UserWorkPreference workPreference;
    private String resume;
    private String city;
    private String country;
    private Boolean openToTravel;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
}

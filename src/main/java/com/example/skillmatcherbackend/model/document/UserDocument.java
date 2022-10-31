package com.example.skillmatcherbackend.model.document;

import com.example.skillmatcherbackend.model.UserRole;
import com.example.skillmatcherbackend.model.UserWorkPreference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDocument implements UserDetails {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
    private UserRole role;
    private String photo;
    private List<String> skills;
    private UserWorkPreference workPreference;
    private String resume;
    private String city;
    private String country;
    private Boolean openToTravel;
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
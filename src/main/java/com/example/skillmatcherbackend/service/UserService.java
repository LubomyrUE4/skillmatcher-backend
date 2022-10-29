package com.example.skillmatcherbackend.service;

import com.example.skillmatcherbackend.exception.DefaultException;
import com.example.skillmatcherbackend.model.ProfileResponse;
import com.example.skillmatcherbackend.model.document.UserDocument;
import com.example.skillmatcherbackend.model.dto.ProfileDTO;
import com.example.skillmatcherbackend.model.dto.SignUpDTO;
import com.example.skillmatcherbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public List<ProfileResponse> getProfiles() {
        final List<ProfileResponse> profileResponseList = new ArrayList<>();
        for (final UserDocument user : userRepository.findAll()) {
            profileResponseList.add(getProfileResponseByUser(user));
        }
        return profileResponseList;
    }

    public ProfileResponse getProfileById(final String id) throws DefaultException {
        final UserDocument userDocument = userRepository.findById(id).orElseThrow(() -> new DefaultException("User does not exist"));
        return getProfileResponseByUser(userDocument);
    }

    private static ProfileResponse getProfileResponseByUser(final UserDocument userDocument) {
        final ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(userDocument.getId());
        profileResponse.setEmail(userDocument.getEmail());
        profileResponse.setUserName(userDocument.getUsername());
        profileResponse.setResume(userDocument.getResume());
        profileResponse.setCountry(userDocument.getCountry());
        profileResponse.setOpenToTravel(userDocument.getOpenToTravel());
        profileResponse.setPrimaryPhoneNumber(userDocument.getPrimaryPhoneNumber());
        profileResponse.setSecondaryPhoneNumber(userDocument.getSecondaryPhoneNumber());
        profileResponse.setCity(userDocument.getCity());
        profileResponse.setSkills(userDocument.getSkills());
        profileResponse.setWorkPreference(userDocument.getWorkPreference());
        profileResponse.setPhoto(userDocument.getPhoto());
        profileResponse.setRole(userDocument.getRole());
        return profileResponse;
    }

    public UserDocument saveUser(final SignUpDTO signUpDTO) throws DefaultException {
        if (userRepository.findByEmail(signUpDTO.getEmail()) != null) {
            throw new DefaultException("User already exists");
        }
        return userRepository.insert(getUserBySignUpForm(signUpDTO));
    }

    private UserDocument getUserBySignUpForm(final SignUpDTO signUpDTO) {
        final UserDocument userDocument = new UserDocument();
        userDocument.setUserName(signUpDTO.getUserName());
        userDocument.setEmail(signUpDTO.getEmail());
        userDocument.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        userDocument.setRole(signUpDTO.getRole());
        return userDocument;
    }

    public UserDocument updateProfile(final ProfileDTO profileDTO) throws DefaultException {
        final UserDocument updatedUser = getUserByProfileDTO(profileDTO);
        final UserDocument existingUser = getUserByIdIfExists(updatedUser);
        updatedUser.setUserName(existingUser.getUsername());
        updatedUser.setEmail(existingUser.getEmail());
        updatedUser.setPassword(existingUser.getPassword());
        updatedUser.setRole(existingUser.getRole());
        return userRepository.save(updatedUser);
    }

    private static UserDocument getUserByProfileDTO(final ProfileDTO profileDTO) {
        final UserDocument userDocument = new UserDocument();
        userDocument.setId(profileDTO.getId());
        userDocument.setCity(profileDTO.getCity());
        userDocument.setResume(profileDTO.getResume());
        userDocument.setCountry(profileDTO.getCountry());
        userDocument.setOpenToTravel(profileDTO.getOpenToTravel());
        userDocument.setPrimaryPhoneNumber(profileDTO.getPrimaryPhoneNumber());
        userDocument.setSecondaryPhoneNumber(profileDTO.getSecondaryPhoneNumber());
        userDocument.setSkills(profileDTO.getSkills());
        userDocument.setWorkPreference(profileDTO.getWorkPreference());
        userDocument.setPhoto(profileDTO.getPhoto());
        return userDocument;
    }

    private UserDocument getUserByIdIfExists(final UserDocument userDocument) throws DefaultException {
        if (userRepository.findById(userDocument.getId()).isEmpty()) {
            throw new DefaultException("User does not exist");
        } else {
            return userRepository.findById(userDocument.getId()).get();
        }
    }
}
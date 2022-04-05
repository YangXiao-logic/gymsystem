package com.gym.service;

import com.gym.mbg.model.Facility;
import com.gym.mbg.model.UmsPermission;

import java.util.List;

public interface FacilityService {

    List<Facility> listAllFacility();

    Facility getFacility(int id);

    Facility getFacilityByName(String facilityName);


}

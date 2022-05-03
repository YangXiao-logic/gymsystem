package com.gym.service;

import com.gym.mbg.model.Facility;

import java.util.Date;
import java.util.List;

public interface FacilityService {

    List<Facility> getFacilityListByDay(Date day);

    Facility getFacilityInfoByIdAndDay(int id, Date day);

    List<Date> getFacilityOpenDay(Facility facility);



}

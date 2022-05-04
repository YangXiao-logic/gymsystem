package com.gym.service;

import com.gym.mbg.model.Facility;
import com.gym.mbg.model.FacilityTimeTable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface FacilityService {


    Date toDate(LocalDateTime localDateTime);

    LocalDateTime toDateTime(Date date);

    List<Facility> getFacilityListByDay(Date day);

    //View timetable of specified facility on a range of time/days

    Facility getFacility(int id);

    int leftCapacity(int facility_id, Date time);



    List<FacilityTimeTable> getFacilityOpenDay(int facility_id);
}

package com.gym.service;

import com.gym.mbg.model.Facility;
import com.gym.mbg.model.SingleActivity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SingleActivityService {

    Date toDate(LocalDateTime localDateTime);

    List<SingleActivity> selectByFacilityByWeek(Facility facility);

    List<SingleActivity> selectByUser(int user_id);

    void createSingleActivityByFacilityByTime(Facility facility, Date time);

    int createSingleActivityForAllFacilityForWeek();

}

package com.gym.service;

import com.gym.mbg.model.Facility;
import com.gym.mbg.model.SingleOrder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SingleOrderService {

    Date toDate(LocalDateTime localDateTime);

    List<SingleOrder> selectByFacilityByWeek(Facility facility);

    List<SingleOrder> selectByUser(int user_id);

    void createSingleActivityByFacilityByTime(Facility facility, Date time);

    int createSingleActivityForAllFacilityForWeek();

}

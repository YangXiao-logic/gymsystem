package com.gym.service.impl;

import com.gym.mbg.mapper.FacilityMapper;
import com.gym.mbg.mapper.SingleActivityMapper;
import com.gym.mbg.model.Facility;
import com.gym.mbg.model.SingleActivity;
import com.gym.mbg.model.SingleActivityExample;
import com.gym.service.FacilityService;
import com.gym.service.SingleActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class SingleActivityServiceImpl implements SingleActivityService {

    @Autowired
    FacilityMapper facilityMapper;

    @Autowired
    FacilityService facilityService;

    @Autowired
    SingleActivityMapper singleActivityMapper;

    @Override
    public Date toDate(LocalDateTime localDateTime){
        return new Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @Override
    public List<SingleActivity> selectByFacilityByWeek(Facility facility) {
        SingleActivityExample singleActivityExample = new SingleActivityExample();
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.MIN);
        singleActivityExample.createCriteria().andFacilityIdEqualTo(facility.getId())
                .andStartTimeGreaterThan(toDate(startTime)).andStartTimeLessThan(toDate(endTime));

        return singleActivityMapper.selectByExample(singleActivityExample);
    }

    @Override
    public List<SingleActivity> selectByUser(int user_id) {
        return null;
    }

    @Override
    public void createSingleActivityByFacilityByTime(Facility facility, Date time) {
        int facilityId = facility.getId();
        SingleActivity singleActivity = new SingleActivity();
        singleActivity.setFacilityId(facilityId);
        singleActivity.setStartTime(time);
        singleActivityMapper.insertSelective(singleActivity);
    }

    @Override
    public int createSingleActivityForAllFacilityForWeek() {

        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIN);

        for(int i=0;i<1;i++){
            localDateTime=localDateTime.plusHours(10);
            for (int j=0;j<8;j++){
                for(int k=1;k<5;k++){
                    Facility facility = facilityService.getFacility(k);
                    localDateTime = localDateTime.plusHours(1);
                    Date date = toDate(localDateTime);
                    createSingleActivityByFacilityByTime(facility, date);
                }
            }
        }
        return 1;
    }
}

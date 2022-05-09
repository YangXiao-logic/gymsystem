package com.gym.service.impl;

import com.gym.mbg.mapper.FacilityMapper;
import com.gym.mbg.mapper.SingleOrderMapper;
import com.gym.mbg.model.Facility;
import com.gym.mbg.model.SingleOrder;
import com.gym.mbg.model.SingleOrderExample;
import com.gym.service.FacilityService;
import com.gym.service.SingleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class SingleOrderServiceImpl implements SingleOrderService {

    @Autowired
    FacilityMapper facilityMapper;

    @Autowired
    FacilityService facilityService;

    @Autowired
    SingleOrderMapper singleOrderMapper;

    @Override
    public Date toDate(LocalDateTime localDateTime){
        return new Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }


    public LocalDateTime toDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public List<SingleOrder> selectByFacilityByWeek(Facility facility) {
        SingleOrderExample singleOrderExample = new SingleOrderExample();
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.MIN);
        singleOrderExample.createCriteria().andFacilityIdEqualTo(facility.getId())
                .andStartTimeGreaterThan(toDate(startTime)).andStartTimeLessThan(toDate(endTime));

        return singleOrderMapper.selectByExample(singleOrderExample);
    }

    @Override
    public List<SingleOrder> selectByUser(int user_id) {
        return null;
    }

    @Override
    public void createSingleActivityByFacilityByTime(Facility facility, Date time) {
        int facilityId = facility.getId();
        SingleOrder singleOrder = new SingleOrder();
        singleOrder.setFacilityId(facilityId);
        singleOrder.setStartTime(time);
        singleOrderMapper.insertSelective(singleOrder);
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

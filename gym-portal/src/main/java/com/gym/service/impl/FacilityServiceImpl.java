package com.gym.service.impl;

import com.gym.mbg.mapper.FacilityMapper;
import com.gym.mbg.mapper.FacilityTimeTableMapper;
import com.gym.mbg.mapper.SingleOrderMapper;
import com.gym.mbg.model.*;
import com.gym.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private FacilityTimeTableMapper facilityTimeTableMapper;
    @Autowired
    private SingleOrderMapper singleOrderMapper;

    @Override
    public Date toDate(LocalDateTime localDateTime) {
        return new Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @Override
    public LocalDateTime toDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public List<Facility> getFacilityListByDay(Date day) {
        FacilityTimeTableExample facilityTimeTableExample = new FacilityTimeTableExample();
        FacilityExample facilityExample = new FacilityExample();
        Date nextDay = toDate(toDateTime(day).plusDays(1));
        facilityTimeTableExample.createCriteria().andOpenTimeGreaterThan(day).andCloseTimeLessThan(nextDay);
        List<FacilityTimeTable> facilityTimeTableList = facilityTimeTableMapper.selectByExample(facilityTimeTableExample);
        for (FacilityTimeTable facilityTimeTable : facilityTimeTableList) {
            int id = facilityTimeTable.getFacilityId();
            FacilityExample.Criteria c1 = facilityExample.createCriteria().andIdEqualTo(id);
            facilityExample.or(c1);
        }

        return facilityMapper.selectByExample(facilityExample);
    }

    @Override
    public Facility getFacility(int id) {
        return facilityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int leftCapacity(int facility_id, Date time) {
        SingleOrderExample singleOrderExample = new SingleOrderExample();
        singleOrderExample.createCriteria().andFacilityIdEqualTo(facility_id).andStartTimeEqualTo(time);
        long count = singleOrderMapper.countByExample(singleOrderExample);
        long leftCapacity = facilityMapper.selectByPrimaryKey(facility_id).getCapacity() - count;
        return (int) leftCapacity;
    }

    @Override
    public List<FacilityTimeTable> getFacilityOpenDay(int facility_id) {
        FacilityTimeTableExample facilityTimeTableExample = new FacilityTimeTableExample();
        facilityTimeTableExample.createCriteria().andFacilityIdEqualTo(facility_id).andCloseTimeGreaterThan(toDate(LocalDateTime.now()));
        return facilityTimeTableMapper.selectByExample(facilityTimeTableExample);
    }
}

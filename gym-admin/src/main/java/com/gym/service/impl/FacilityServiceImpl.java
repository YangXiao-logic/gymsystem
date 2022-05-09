package com.gym.service.impl;

import com.gym.mbg.mapper.FacilityMapper;
import com.gym.mbg.mapper.FacilityTimeTableMapper;
import com.gym.mbg.model.Facility;
import com.gym.mbg.model.FacilityExample;
import com.gym.mbg.model.FacilityTimeTable;
import com.gym.service.FacilityService;
import com.gym.service.SingleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private SingleOrderService singleOrderService;
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private FacilityTimeTableMapper facilityTimeTableMapper;

    @Override
    public List<Facility> listAllFacility() {
        return facilityMapper.selectByExample(new FacilityExample());
    }

    public Facility getFacility(int id) {
        return facilityMapper.selectByPrimaryKey(id);
    }

    @Override
    public Facility getFacilityByName(String facilityName) {
        FacilityExample example = new FacilityExample();
        example.createCriteria().andFacilityNameEqualTo(facilityName);
        return facilityMapper.selectByExample(example).get(0);
    }

    @Override
    public int openFacility(FacilityTimeTable facilityTimeTable) {
        return facilityTimeTableMapper.insertSelective(facilityTimeTable);
    }

    @Override
    public int openNextWeekAllFacility() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIN);
        LocalDateTime localDateTimeOpenTime = localDateTime.plusHours(8);
        LocalDateTime localDateTimeCloseTime = localDateTime.plusHours(22);
        for(int i=0;i<7;i++){
            for (int j=1;j<5;j++){
                FacilityTimeTable facilityTimeTable=new FacilityTimeTable();
                facilityTimeTable.setFacilityId(j);
                facilityTimeTable.setOpenTime(singleOrderService.toDate(localDateTimeOpenTime));
                facilityTimeTable.setCloseTime(singleOrderService.toDate(localDateTimeCloseTime));
                openFacility(facilityTimeTable);
            }
            localDateTimeOpenTime=localDateTimeOpenTime.plusDays(1);
            localDateTimeCloseTime=localDateTimeCloseTime.plusDays(1);
        }
        return 1;
    }


}

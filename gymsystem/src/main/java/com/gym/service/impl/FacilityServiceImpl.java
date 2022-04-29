package com.gym.service.impl;

import com.gym.mbg.mapper.FacilityMapper;
import com.gym.mbg.model.Facility;
import com.gym.mbg.model.FacilityExample;
import com.gym.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityMapper facilityMapper;

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


}

package com.gym.mbg.mapper;

import com.gym.mbg.model.Facility;
import com.gym.mbg.model.FacilityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FacilityMapper {
    long countByExample(FacilityExample example);

    int deleteByExample(FacilityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Facility record);

    int insertSelective(Facility record);

    List<Facility> selectByExample(FacilityExample example);

    Facility selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Facility record, @Param("example") FacilityExample example);

    int updateByExample(@Param("record") Facility record, @Param("example") FacilityExample example);

    int updateByPrimaryKeySelective(Facility record);

    int updateByPrimaryKey(Facility record);
}
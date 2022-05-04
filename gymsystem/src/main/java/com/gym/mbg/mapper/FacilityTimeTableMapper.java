package com.gym.mbg.mapper;

import com.gym.mbg.model.FacilityTimeTable;
import com.gym.mbg.model.FacilityTimeTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FacilityTimeTableMapper {
    long countByExample(FacilityTimeTableExample example);

    int deleteByExample(FacilityTimeTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FacilityTimeTable record);

    int insertSelective(FacilityTimeTable record);

    List<FacilityTimeTable> selectByExample(FacilityTimeTableExample example);

    FacilityTimeTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FacilityTimeTable record, @Param("example") FacilityTimeTableExample example);

    int updateByExample(@Param("record") FacilityTimeTable record, @Param("example") FacilityTimeTableExample example);

    int updateByPrimaryKeySelective(FacilityTimeTable record);

    int updateByPrimaryKey(FacilityTimeTable record);
}
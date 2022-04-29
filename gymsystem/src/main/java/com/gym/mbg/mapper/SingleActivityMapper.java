package com.gym.mbg.mapper;

import com.gym.mbg.model.SingleActivity;
import com.gym.mbg.model.SingleActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SingleActivityMapper {
    long countByExample(SingleActivityExample example);

    int deleteByExample(SingleActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleActivity record);

    int insertSelective(SingleActivity record);

    List<SingleActivity> selectByExample(SingleActivityExample example);

    SingleActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleActivity record, @Param("example") SingleActivityExample example);

    int updateByExample(@Param("record") SingleActivity record, @Param("example") SingleActivityExample example);

    int updateByPrimaryKeySelective(SingleActivity record);

    int updateByPrimaryKey(SingleActivity record);
}
package com.gym.mbg.mapper;

import com.gym.mbg.model.SingleOrder;
import com.gym.mbg.model.SingleOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SingleOrderMapper {
    long countByExample(SingleOrderExample example);

    int deleteByExample(SingleOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleOrder record);

    int insertSelective(SingleOrder record);

    List<SingleOrder> selectByExample(SingleOrderExample example);

    SingleOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleOrder record, @Param("example") SingleOrderExample example);

    int updateByExample(@Param("record") SingleOrder record, @Param("example") SingleOrderExample example);

    int updateByPrimaryKeySelective(SingleOrder record);

    int updateByPrimaryKey(SingleOrder record);
}
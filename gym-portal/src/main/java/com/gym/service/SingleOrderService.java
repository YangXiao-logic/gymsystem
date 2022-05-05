package com.gym.service;

import com.gym.mbg.model.SingleOrder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SingleOrderService {

    List<SingleOrder> currentCustomerTotalOrder();


    int order(SingleOrder singleOrder);

    int cancel(int facility_id, Date date);
}

package com.gym.service.impl;

import com.gym.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerServiceImpl implements ManagerService {

    @Autowired

    @Override
    public int countTotalCustomer() {
        return 0;
    }

    @Override
    public int countNewCustomerByMouth() {
        return 0;
    }

    @Override
    public int countAverageExerciseTime() {
        return 0;
    }
}

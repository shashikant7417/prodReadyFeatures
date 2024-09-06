package com.prod_ready_feature.production_ready_feature.client;

import com.prod_ready_feature.production_ready_feature.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    EmployeeDto getEmployeeByID(Long employeeId);
    EmployeeDto createEmployee(EmployeeDto employeeDto);

}

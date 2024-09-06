package com.prod_ready_feature.production_ready_feature.client.impl;

import com.prod_ready_feature.production_ready_feature.client.EmployeeClient;
import com.prod_ready_feature.production_ready_feature.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);


    @Override
    public EmployeeDto getEmployeeByID(Long employeeId) {


            EmployeeDto employeeDto = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDto;

    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {


        EmployeeDto employeeDto1 = restClient.post().uri("employees")
                .body(employeeDto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return employeeDto1;
    }
}

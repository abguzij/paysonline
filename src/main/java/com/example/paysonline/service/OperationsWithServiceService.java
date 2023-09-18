package com.example.paysonline.service;

import com.example.paysonline.dto.ServiceGroupResponseDto;
import com.example.paysonline.dto.ServiceProviderResponseDto;
import com.example.paysonline.dto.ServiceResponseDto;
import com.example.paysonline.entity.ServiceEntity;
import com.example.paysonline.entity.ServiceGroupEntity;
import com.example.paysonline.entity.ServiceProviderEntity;
import com.example.paysonline.exception.ServiceProviderGroupNotFoundException;
import com.example.paysonline.exception.ServiceProviderNotFoundException;
import com.querydsl.core.types.Predicate;

import javax.management.ServiceNotFoundException;
import java.util.List;

public interface OperationsWithServiceService {
    List<ServiceProviderResponseDto> getAllServiceProviders(Predicate predicate) throws ServiceProviderNotFoundException;

    List<ServiceGroupResponseDto> getAllServiceGroups(Predicate predicate) throws ServiceProviderGroupNotFoundException;

    List<ServiceResponseDto> getAllServices(Predicate predicate) throws ServiceNotFoundException;

}

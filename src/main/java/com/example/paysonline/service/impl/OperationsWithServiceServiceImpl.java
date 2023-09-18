package com.example.paysonline.service.impl;

import com.example.paysonline.dto.ServiceGroupResponseDto;
import com.example.paysonline.dto.ServiceProviderResponseDto;
import com.example.paysonline.dto.ServiceResponseDto;
import com.example.paysonline.entity.ServiceEntity;
import com.example.paysonline.entity.ServiceGroupEntity;
import com.example.paysonline.entity.ServiceProviderEntity;
import com.example.paysonline.exception.ServiceProviderGroupNotFoundException;
import com.example.paysonline.exception.ServiceProviderNotFoundException;
import com.example.paysonline.mapper.ServiceMapper;
import com.example.paysonline.repository.ServiceEntityRepository;
import com.example.paysonline.repository.ServiceGroupEntityRepository;
import com.example.paysonline.repository.ServiceProviderEntityRepository;
import com.example.paysonline.service.OperationsWithServiceService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OperationsWithServiceServiceImpl implements OperationsWithServiceService {
    private final ServiceProviderEntityRepository serviceProviderEntityRepository;
    private final ServiceGroupEntityRepository serviceGroupEntityRepository;
    private final ServiceEntityRepository serviceEntityRepository;

    @Autowired
    public OperationsWithServiceServiceImpl(
            ServiceProviderEntityRepository serviceProviderEntityRepository,
            ServiceGroupEntityRepository serviceGroupEntityRepository,
            ServiceEntityRepository serviceEntityRepository
    ) {
        this.serviceProviderEntityRepository = serviceProviderEntityRepository;
        this.serviceGroupEntityRepository = serviceGroupEntityRepository;
        this.serviceEntityRepository = serviceEntityRepository;
    }

    @Override
    public List<ServiceProviderResponseDto> getAllServiceProviders(Predicate predicate)
            throws ServiceProviderNotFoundException
    {
        Iterable<ServiceProviderEntity> serviceProviderEntities
                = this.serviceProviderEntityRepository.findAll(predicate);
        List<ServiceProviderResponseDto> serviceProviderResponseDtoList =
                StreamSupport
                        .stream(serviceProviderEntities.spliterator(), false)
                        .map(ServiceMapper::mapServiceProviderEntityToDto)
                        .collect(Collectors.toList());

        if(serviceProviderResponseDtoList.isEmpty()){
            throw new ServiceProviderNotFoundException("Поставщика услуг по данным параметрам не найдено!");
        }
        return serviceProviderResponseDtoList;
    }

    @Override
    public List<ServiceGroupResponseDto> getAllServiceGroups(Predicate predicate)
            throws ServiceProviderGroupNotFoundException
    {
        Iterable<ServiceGroupEntity> serviceGroupEntities
                = this.serviceGroupEntityRepository.findAll(predicate);
        List<ServiceGroupResponseDto> serviceGroupResponseDtoList = StreamSupport
                .stream(serviceGroupEntities.spliterator(), false)
                .map(ServiceMapper::mapServiceGroupEntityToDto)
                .collect(Collectors.toList());

        if(serviceGroupResponseDtoList.isEmpty()) {
            throw new ServiceProviderGroupNotFoundException("Группы услуг по данным парметрам не найдено!");
        }
        return serviceGroupResponseDtoList;
    }

    @Override
    public List<ServiceResponseDto> getAllServices(Predicate predicate) throws ServiceNotFoundException {
        Iterable<ServiceEntity> serviceEntities
                = this.serviceEntityRepository.findAll(predicate);
        List<ServiceResponseDto> serviceResponseDtoList = StreamSupport
                .stream(serviceEntities.spliterator(), false)
                .map(ServiceMapper::mapServiceEntityToDto)
                .collect(Collectors.toList());

        if (serviceResponseDtoList.isEmpty()) {
            throw new ServiceNotFoundException("Услуг по данным параметрам не найдено!");
        }
        return serviceResponseDtoList;
    }

}

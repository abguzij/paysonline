package com.example.paysonline.controller.v1;

import com.example.paysonline.dto.ServiceResponseDto;
import com.example.paysonline.dto.ServiceGroupResponseDto;
import com.example.paysonline.dto.ServiceProviderResponseDto;
import com.example.paysonline.entity.ServiceEntity;
import com.example.paysonline.entity.ServiceGroupEntity;
import com.example.paysonline.entity.ServiceProviderEntity;
import com.example.paysonline.exception.ServiceProviderGroupNotFoundException;
import com.example.paysonline.exception.ServiceProviderNotFoundException;
import com.example.paysonline.service.OperationsWithServiceService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final OperationsWithServiceService operationsWithServiceService;

    @Autowired
    public ServiceController(OperationsWithServiceService operationsWithServiceService) {
        this.operationsWithServiceService = operationsWithServiceService;
    }

    @GetMapping("/providers")
    public List<ServiceProviderResponseDto> getAllServiceProviders(
            @QuerydslPredicate(root = ServiceProviderEntity.class) Predicate predicate
    )
            throws ServiceProviderNotFoundException
    {
        return this.operationsWithServiceService.getAllServiceProviders(predicate);
    }

    @GetMapping("/groups")
    public List<ServiceGroupResponseDto> getAllServiceGroups(
            @QuerydslPredicate(root = ServiceGroupEntity.class) Predicate predicate
    )
            throws ServiceProviderGroupNotFoundException
    {
        return this.operationsWithServiceService.getAllServiceGroups(predicate);
    }

    @GetMapping("/all-services")
    public List<ServiceResponseDto> getAllServices(
            @QuerydslPredicate(root = ServiceEntity.class) Predicate predicate
    )
            throws ServiceNotFoundException
    {
        return this.operationsWithServiceService.getAllServices(predicate);
    }
}

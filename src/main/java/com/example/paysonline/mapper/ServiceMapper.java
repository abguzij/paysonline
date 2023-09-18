package com.example.paysonline.mapper;

import com.example.paysonline.dto.ServiceResponseDto;
import com.example.paysonline.dto.ServiceGroupResponseDto;
import com.example.paysonline.dto.ServiceProviderResponseDto;
import com.example.paysonline.entity.ServiceEntity;
import com.example.paysonline.entity.ServiceGroupEntity;
import com.example.paysonline.entity.ServiceProviderEntity;

public class ServiceMapper {
    public static ServiceProviderResponseDto mapServiceProviderEntityToDto(ServiceProviderEntity source){
        return new ServiceProviderResponseDto()
                .setId(source.getId())
                .setTitle(source.getTitle());
    }

    public static ServiceProviderEntity mapServiceProviderDtoToEntity(ServiceProviderResponseDto dto){
        return new ServiceProviderEntity()
                .setId(dto.getId())
                .setTitle(dto.getTitle());
    }

    public static ServiceGroupResponseDto mapServiceGroupEntityToDto(ServiceGroupEntity source) {
        return new ServiceGroupResponseDto()
                .setId(source.getId())
                .setTitle(source.getTitle())
                .setServiceProviderDto(
                        ServiceMapper.mapServiceProviderEntityToDto(source.getServiceProviderEntity())
                );
    }

    public static ServiceGroupEntity mapServiceGroupDtoToEntity(ServiceGroupResponseDto dto) {
        return new ServiceGroupEntity()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setServiceProviderEntity(
                        ServiceMapper.mapServiceProviderDtoToEntity(dto.getServiceProviderDto())
                );
    }

    public static ServiceResponseDto mapServiceEntityToDto(ServiceEntity source) {
        return new ServiceResponseDto()
                .setId(source.getId())
                .setTitle(source.getTitle())
                .setCommission(source.getCommission())
                .setMinPaymentAmount(source.getMinPaymentAmount())
                .setServiceGroupDto(
                        ServiceMapper.mapServiceGroupEntityToDto(source.getServiceGroupEntity())
                );
    }

    public static ServiceEntity mapServiceDtoToEntity(ServiceResponseDto dto){
        return new ServiceEntity()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setCommission(dto.getCommission())
                .setMinPaymentAmount(dto.getMinPaymentAmount())
                .setServiceGroupEntity(
                        ServiceMapper.mapServiceGroupDtoToEntity(dto.getServiceGroupDto())
                );
    }
}

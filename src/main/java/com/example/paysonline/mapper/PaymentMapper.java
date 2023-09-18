package com.example.paysonline.mapper;

import com.example.paysonline.dto.PaymentHistoryRequestDto;
import com.example.paysonline.dto.PaymentHistoryResponseDto;
import com.example.paysonline.dto.ProviderDetailsResponseDto;
import com.example.paysonline.dto.TransactionsHistoryResponseDto;
import com.example.paysonline.entity.PaymentHistoryEntity;
import com.example.paysonline.entity.ProviderDetailsEntity;
import com.example.paysonline.entity.TransactionsHistoryEntity;

public class PaymentMapper {
    public static PaymentHistoryResponseDto mapPaymentHistoryEntityToDto(PaymentHistoryEntity source){
        return new PaymentHistoryResponseDto()
                .setId(source.getId())
                .setClientFullName(source.getClientFullName())
                .setClientCredentials(source.getClientCredentials())
                .setPaymentAmount(source.getPaymentAmount())
                .setCreatedAt(source.getCreatedAt())
                .setServiceResponseDto(
                        ServiceMapper.mapServiceEntityToDto(source.getServiceEntity())
                );
    }

    public static ProviderDetailsResponseDto mapProviderDetailsEntityToDto(ProviderDetailsEntity source) {
        return new ProviderDetailsResponseDto()
                .setId(source.getId())
                .setBankAccount(source.getBankAccount())
                .setServiceProviderDto(
                        ServiceMapper.mapServiceProviderEntityToDto(source.getServiceProviderEntity())
                )
                .setServiceGroupDto(
                        ServiceMapper.mapServiceGroupEntityToDto(source.getServiceGroupEntity())
                );
    }

    public static ProviderDetailsEntity mapProviderDetailsDtoToEntity(ProviderDetailsResponseDto dto){
        return new ProviderDetailsEntity()
                .setId(dto.getId())
                .setBankAccount(dto.getBankAccount())
                .setServiceProviderEntity(
                        ServiceMapper.mapServiceProviderDtoToEntity(dto.getServiceProviderDto())
                )
                .setServiceGroupEntity(
                        ServiceMapper.mapServiceGroupDtoToEntity(dto.getServiceGroupDto())
                );
    }

    public static TransactionsHistoryResponseDto mapTransactionsHistoryEntityToDto(TransactionsHistoryEntity source) {
        return new TransactionsHistoryResponseDto()
                .setId(source.getId())
                .setPaymentAmount(source.getPaymentAmount())
                .setServiceResponseDto(
                        ServiceMapper.mapServiceEntityToDto(source.getServiceEntity())
                )
                .setCreatedAt(source.getCreatedAt())
                .setProviderDetailsResponseDto(
                        PaymentMapper.mapProviderDetailsEntityToDto(source.getProviderDetailsEntity())
                );
    }

    public static TransactionsHistoryEntity mapTransactionsHistoryDtoToEntity(TransactionsHistoryResponseDto dto) {
        return new TransactionsHistoryEntity()
                .setId(dto.getId())
                .setPaymentAmount(dto.getPaymentAmount())
                .setServiceEntity(
                        ServiceMapper.mapServiceDtoToEntity(dto.getServiceResponseDtoDto())
                )
                .setProviderDetailsEntity(
                        PaymentMapper.mapProviderDetailsDtoToEntity(dto.getProviderDetailsResponseDto())
                );
    }

    public static PaymentHistoryEntity mapPaymentHistoryRequestDtoToEntity(PaymentHistoryRequestDto dto){
        return new PaymentHistoryEntity()
                .setClientFullName(dto.getFullName())
                .setPaymentAmount(dto.getPaymentAmount())
                .setClientCredentials(dto.getClientCredentials());
    }
}

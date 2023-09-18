package com.example.paysonline.service.impl;

import com.example.paysonline.dto.PaymentHistoryRequestDto;
import com.example.paysonline.dto.PaymentHistoryResponseDto;
import com.example.paysonline.dto.TransactionsHistoryResponseDto;
import com.example.paysonline.entity.*;
import com.example.paysonline.entity.atribute.PaymentStatus;
import com.example.paysonline.exception.IncorrectAmountException;
import com.example.paysonline.exception.InvalidPaymentHistoryEntityException;
import com.example.paysonline.exception.PaymentHistoryEntityDoesntExistException;
import com.example.paysonline.exception.ProviderDetailsNotFoundException;
import com.example.paysonline.mapper.PaymentMapper;
import com.example.paysonline.repository.PaymentHistoryEntityRepository;
import com.example.paysonline.repository.ProviderDetailsEntityRepository;
import com.example.paysonline.repository.ServiceEntityRepository;
import com.example.paysonline.repository.TransactionsHistoryEntityRepository;
import com.example.paysonline.response.PaymentDeclinedResponse;
import com.example.paysonline.service.PaymentService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHistoryEntityRepository paymentHistoryEntityRepository;
    private final ProviderDetailsEntityRepository providerDetailsEntityRepository;
    private final TransactionsHistoryEntityRepository transactionsHistoryEntityRepository;
    private final ServiceEntityRepository serviceEntityRepository;

    @Autowired
    public PaymentServiceImpl(
            PaymentHistoryEntityRepository paymentHistoryEntityRepository,
            ProviderDetailsEntityRepository providerDetailsEntityRepository,
            TransactionsHistoryEntityRepository transactionsHistoryEntityRepository,
            ServiceEntityRepository serviceEntityRepository
    ) {
        this.paymentHistoryEntityRepository = paymentHistoryEntityRepository;
        this.providerDetailsEntityRepository = providerDetailsEntityRepository;
        this.transactionsHistoryEntityRepository = transactionsHistoryEntityRepository;
        this.serviceEntityRepository = serviceEntityRepository;
    }

    @Override
    @Transactional
    public PaymentHistoryResponseDto createNewPayment(PaymentHistoryRequestDto paymentHistoryRequestDto)
            throws IncorrectAmountException
    {
        PaymentHistoryEntity paymentHistoryEntity =
                PaymentMapper.mapPaymentHistoryRequestDtoToEntity(paymentHistoryRequestDto);
        paymentHistoryEntity.setServiceEntity(
                this.serviceEntityRepository.getServiceEntityById(paymentHistoryRequestDto.getServiceId())
        );
        paymentHistoryEntity.setCreatedAt(LocalDateTime.now());

        validatePaymentAmount(paymentHistoryEntity);

        return PaymentMapper.mapPaymentHistoryEntityToDto(
                this.paymentHistoryEntityRepository.save(paymentHistoryEntity)
        );
    }

    @Override
    @Transactional
    public TransactionsHistoryResponseDto confirmPayment(Long paymentId)
            throws PaymentHistoryEntityDoesntExistException,
            InvalidPaymentHistoryEntityException,
            ProviderDetailsNotFoundException
    {
        PaymentHistoryEntity paymentHistoryEntity
                = this.paymentHistoryEntityRepository.getPaymentHistoryEntityById(paymentId);
        if(Objects.isNull(paymentHistoryEntity)){
            throw new PaymentHistoryEntityDoesntExistException(
                    "Записи в истории платежей с таким ID не существует!"
            );
        }
        if (Objects.nonNull(paymentHistoryEntity.getConfirmed())){
            throw new InvalidPaymentHistoryEntityException(
                    "Введен неверный ID записи истории платежей"
            );
        }

        paymentHistoryEntity.setConfirmed(PaymentStatus.ACCEPTED);
        this.paymentHistoryEntityRepository.save(paymentHistoryEntity);

        return PaymentMapper.mapTransactionsHistoryEntityToDto(
                this.transactionsHistoryEntityRepository.save(
                        this.createNewTransactionByPaymentHistoryEntity(paymentHistoryEntity)
                )
        );
    }

    @Override
    @Transactional
    public PaymentDeclinedResponse declinePayment(Long paymentId)
            throws PaymentHistoryEntityDoesntExistException,
            InvalidPaymentHistoryEntityException
    {
        PaymentHistoryEntity paymentHistoryEntity
                = this.paymentHistoryEntityRepository.getPaymentHistoryEntityById(paymentId);
        if(Objects.isNull(paymentHistoryEntity)){
            throw new PaymentHistoryEntityDoesntExistException(
                    "Записи в истории платежей с таким ID не существует!"
            );
        }
        if (Objects.nonNull(paymentHistoryEntity.getConfirmed())){
            throw new InvalidPaymentHistoryEntityException(
                    "Введен неверный ID записи истории платежей"
            );
        }
        paymentHistoryEntity.setConfirmed(PaymentStatus.DECLINED);
        this.paymentHistoryEntityRepository.save(paymentHistoryEntity);

        return new PaymentDeclinedResponse()
            .setHttpStatus(HttpStatus.OK)
            .setMessage("Платеж был успешно отменен!");
    }

    @Override
    public ProviderDetailsEntity getProviderDetailsByServiceId(Long serviceId)
            throws ProviderDetailsNotFoundException
    {
        BooleanBuilder builder = new BooleanBuilder();
        QProviderDetailsEntity root = QProviderDetailsEntity.providerDetailsEntity;

        builder.and(root.serviceGroupEntity.serviceEntities.any().id.eq(serviceId));

        Optional<ProviderDetailsEntity> providerDetailsEntityOptional =
                this.providerDetailsEntityRepository.findOne(builder.getValue());
        ProviderDetailsEntity providerDetailsEntity = providerDetailsEntityOptional.orElse(null);
        if(Objects.nonNull(providerDetailsEntity)) {
            return providerDetailsEntity;
        }
        throw new ProviderDetailsNotFoundException(
                "Реквизитов поставщика с такой услугой не найдено!"
        );
    }

    @Override
    public List<PaymentHistoryEntity> getPaymentHistoryByServiceId(Long serviceId) {
        return this.paymentHistoryEntityRepository.getPaymentHistoryEntitiesByServiceEntityId(serviceId);
    }

    @Override
    public List<TransactionsHistoryEntity> getTransactionsHistoryByProviderId(Long providerId) {
        BooleanBuilder builder = new BooleanBuilder();
        QTransactionsHistoryEntity root = QTransactionsHistoryEntity.transactionsHistoryEntity;

        builder.and(root.providerDetailsEntity.serviceProviderEntity.id.eq(providerId));

        Iterable<TransactionsHistoryEntity> transactionsHistoryEntities =
                this.transactionsHistoryEntityRepository.findAll(builder.getValue());
        return StreamSupport
                .stream(transactionsHistoryEntities.spliterator(), false)
                .collect(Collectors.toList());
    }

    private void validatePaymentAmount(PaymentHistoryEntity paymentHistoryEntity)
            throws IncorrectAmountException
    {
        Integer paymentAmountWithCommission = countPaymentAmountWithCommission(paymentHistoryEntity);
        if(paymentAmountWithCommission < paymentHistoryEntity.getServiceEntity().getMinPaymentAmount()){
            throw new IncorrectAmountException("Сумма платежа с учетом комиссии " +
                    "не может быть меньше минимального размера платежа");
        }
    }

    private Integer countPaymentAmountWithCommission(
            PaymentHistoryEntity paymentHistoryEntity
    ) {
        return paymentHistoryEntity.getPaymentAmount() - paymentHistoryEntity.getServiceEntity().getCommission();
    }

    private TransactionsHistoryEntity createNewTransactionByPaymentHistoryEntity(
            PaymentHistoryEntity paymentHistoryEntity
    ) throws ProviderDetailsNotFoundException {
        return new TransactionsHistoryEntity()
                .setCreatedAt(LocalDateTime.now())
                .setServiceEntity(paymentHistoryEntity.getServiceEntity())
                .setPaymentAmount(this.countPaymentAmountWithCommission(paymentHistoryEntity))
                .setProviderDetailsEntity(
                        this.getProviderDetailsByServiceId(paymentHistoryEntity.getServiceEntity().getId())
                );
    }
}

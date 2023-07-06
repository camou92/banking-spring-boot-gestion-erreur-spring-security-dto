package com.banking.Gesbank.services;

import com.banking.Gesbank.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto> {
    List<TransactionDto> findAllByUserId(Integer userId);
}

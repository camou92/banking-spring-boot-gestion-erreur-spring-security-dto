package com.banking.Gesbank.services.impl;

import com.banking.Gesbank.dto.TransactionSumDetails;
import com.banking.Gesbank.models.TransactionType;
import com.banking.Gesbank.repositories.TransactionRepository;
import com.banking.Gesbank.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final TransactionRepository transactionRepository;
    @Override
    public List<TransactionSumDetails> findSumTractionsByDate(LocalDate startDate, LocalDate endDate, Integer userId) {
        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
        return transactionRepository.findSumTransactionsByDate(start, end, userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountbalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}

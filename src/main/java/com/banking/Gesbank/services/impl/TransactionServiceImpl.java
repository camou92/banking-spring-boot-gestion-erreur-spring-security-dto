package com.banking.Gesbank.services.impl;

import com.banking.Gesbank.dto.TransactionDto;
import com.banking.Gesbank.models.Transaction;
import com.banking.Gesbank.models.TransactionType;
import com.banking.Gesbank.repositories.TransactionRepository;
import com.banking.Gesbank.services.TransactionService;
import com.banking.Gesbank.validators.ObjectsValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository repository;
    private ObjectsValidator<TransactionDto> validator;
    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
        BigDecimal amout = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amout);
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No transaction was found with the ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private int getTransactionMultiplier(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}

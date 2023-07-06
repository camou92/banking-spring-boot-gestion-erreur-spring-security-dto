package com.banking.Gesbank.services.impl;

import com.banking.Gesbank.dto.AccountDto;
import com.banking.Gesbank.exceptions.OperationNonPermittedException;
import com.banking.Gesbank.models.Account;
import com.banking.Gesbank.repositories.AccountRepository;
import com.banking.Gesbank.services.AccountService;
import com.banking.Gesbank.validators.ObjectsValidator;
import lombok.AllArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;
    @Override
    public Integer save(AccountDto dto) {
        // block account update -> iban cannot be changed
        /*if (dto.getId() != null) {
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
            );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount && account.getUser().isActive()) {
            throw new OperationNonPermittedException(
                    "the selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        // generate random IBAN when creating new account else do not update the IBAN

        if (dto.getId() == null) {
            account.setIban(generaterandomIban());
        }

        return accountRepository.save(account).getId();

    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    private String generaterandomIban() {
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        // check if the iban already exists
        boolean ibanExists = accountRepository.findByIban(iban).isPresent();

        // if exists -> generate new random iban
        if (ibanExists) {
            generaterandomIban();

        }
        // if not exist -> return generated iban
        return iban;
    }
}

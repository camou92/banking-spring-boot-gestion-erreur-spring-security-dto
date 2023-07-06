package com.banking.Gesbank.dto;

import com.banking.Gesbank.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {

    private Integer id;

    private String iban;

    private UserDto user;

    public static AccountDto fromEntity(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.fromEntity(account.getUser()))
                .build();
    }

    public static Account toEntity(AccountDto accountDto){
        return Account.builder()
                .id(accountDto.getId())
                .iban(accountDto.getIban())
                .user(UserDto.toEntity(accountDto.getUser()))
                .build();
    }
}

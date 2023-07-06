package com.banking.Gesbank.services;

import com.banking.Gesbank.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {

    List<ContactDto> findAllByUserId(Integer userId);
}

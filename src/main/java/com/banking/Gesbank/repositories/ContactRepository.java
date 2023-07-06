package com.banking.Gesbank.repositories;

import com.banking.Gesbank.dto.ContactDto;
import com.banking.Gesbank.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByUserId(Integer userId);
}

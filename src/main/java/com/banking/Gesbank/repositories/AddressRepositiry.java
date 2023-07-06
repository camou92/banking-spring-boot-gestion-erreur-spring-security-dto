package com.banking.Gesbank.repositories;

import com.banking.Gesbank.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepositiry extends JpaRepository<Address, Integer> {
}

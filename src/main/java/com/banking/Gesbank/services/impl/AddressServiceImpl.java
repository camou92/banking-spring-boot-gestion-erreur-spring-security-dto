package com.banking.Gesbank.services.impl;

import com.banking.Gesbank.dto.AddressDto;
import com.banking.Gesbank.models.Address;
import com.banking.Gesbank.repositories.AddressRepositiry;
import com.banking.Gesbank.services.AddressService;
import com.banking.Gesbank.validators.ObjectsValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepositiry repositiry;
    private ObjectsValidator<AddressDto> validator;
    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repositiry.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repositiry.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repositiry.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No address found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        repositiry.deleteById(id);
    }
}

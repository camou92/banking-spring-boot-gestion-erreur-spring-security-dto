package com.banking.Gesbank.controllers;

import com.banking.Gesbank.dto.AddressDto;
import com.banking.Gesbank.services.AddressService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;
    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto){
        return ResponseEntity.ok(addressService.save(addressDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(addressService.findAll());
    }
    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("address-id") Integer addressId){
        return ResponseEntity.ok(addressService.findById(addressId));
    }
    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(@PathVariable("address-id") Integer addressId){
        addressService.delete(addressId);
        return ResponseEntity.accepted().build();
    }
}

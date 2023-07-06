package com.banking.Gesbank.controllers;

import com.banking.Gesbank.dto.ContactDto;
import com.banking.Gesbank.dto.TransactionDto;
import com.banking.Gesbank.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transact")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction-id") Integer transactionId){
        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDto>> findAllByUserId(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(transactionService.findAllByUserId(userId));
    }
    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(@PathVariable("transaction-id") Integer transactionId){
        transactionService.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}

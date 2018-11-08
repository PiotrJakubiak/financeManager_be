package pl.edu.wat.trainingManager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.wat.trainingManager.model.Transaction;

import java.util.List;


public interface ITransactionService {

    ResponseEntity<Void> save(Transaction transaction);
    ResponseEntity<List<Transaction>> findAll();
    ResponseEntity<Transaction> findOne(String id);
    ResponseEntity<Void> delete(String id);
    ResponseEntity<Void> update(String id, Transaction newTransaction);
    ResponseEntity<List<String>> getMonthIncomeBalance();
    ResponseEntity<List<String>> getMonthSpendingBalance();
}

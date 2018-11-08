package pl.edu.wat.trainingManager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.edu.wat.trainingManager.exceptions.TransactionExistException;
import pl.edu.wat.trainingManager.exceptions.TransactionNotFoundException;
import pl.edu.wat.trainingManager.model.Transaction;
import pl.edu.wat.trainingManager.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TransactionService implements ITransactionService {

    public static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        Assert.notNull(transactionRepository, "TransactionRepository is required");
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<Void> save(Transaction transaction) {
        logger.info("save(Transaction transaction) - Saving transacton =" + transaction);

        System.out.println(transaction.toString());

        if(isTransactionExist(transaction)) {
            throw new TransactionExistException();
        }
        transactionRepository.save(transaction);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Transaction>> findAll() {
        logger.info("findAll() - Searching for all transactions");
        List<Transaction> transactionList = transactionRepository.findAll();
        for(Transaction transaction : transactionList) {
            System.out.println(transaction.toString());
        }

        return new ResponseEntity<List<Transaction>>(transactionList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Transaction> findOne(String id) {
        logger.info("findOne(String id) - Searching transaction, transactionId = " + id);
        Transaction transaction = transactionRepository.findOne(Long.valueOf(id));

        if(null == transaction) {
            throw new TransactionNotFoundException();
        }

        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        logger.info("delete(String id) - Deleting transaction, transactionId = " + id);
        Transaction transaction = transactionRepository.findOne(Long.valueOf(id));

        if(null == transaction) {
            throw new TransactionNotFoundException();
        }

        transactionRepository.delete(transaction);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    private boolean isTransactionExist(Transaction transaction) {
        Transaction newTransaction = transactionRepository.findOne(transaction.getId());
        return newTransaction != null;
    }

    @Override
    public ResponseEntity<Void> update(String id, Transaction newTransaction) {
        logger.info("update(String id, Transaction newTransaction) - Updating transactionId = "
                + id + " new transaction = " + newTransaction );
        Transaction transaction = transactionRepository.findOne(Long.valueOf(id));

        if(null == transaction) {
            throw new TransactionNotFoundException();
        }

        transaction = newTransaction;
        transactionRepository.save(transaction);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getMonthIncomeBalance() {
        System.out.println("Weszlo do getMonthIncomeBalance");
        List<String> monthIncomeBalance = new ArrayList();
        for(int i=0; i<12; i++) {
            monthIncomeBalance.add(new String("0"));
        }
        for(int i=0; i<12; i++) {
            String amount = transactionRepository.findSumAmountByTypePerMonth("Przychod", i + 1, 2018);
            if(amount != null) {
                monthIncomeBalance.set(i, amount);
            }
        }
        for(int i=0; i<12; i++) {
            System.out.println(monthIncomeBalance.get(i));
        }
        System.out.println("--------");
        return new ResponseEntity<List<String>>(monthIncomeBalance, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>>  getMonthSpendingBalance() {
        System.out.println("Weszlo do getMonthSpendingBalance");
        List<String> monthSpendingBalance = new ArrayList();
        for(int i=0; i<12; i++) {
            monthSpendingBalance.add(new String("0"));
        }
        for(int i=0; i<12; i++) {
            String amount = transactionRepository.findSumAmountByTypePerMonth("Wydatek", i + 1, 2018);
            if(amount != null) {
                monthSpendingBalance.set(i, amount);
            }
        }
        for(int i=0; i<12; i++) {
            System.out.println(monthSpendingBalance.get(i));
        }
        System.out.println("--------");
        return new ResponseEntity<List<String>>(monthSpendingBalance, HttpStatus.OK);
    }
}

package pl.edu.wat.trainingManager.controler;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.trainingManager.model.DatabaseStatus;
import pl.edu.wat.trainingManager.model.Transaction;
import pl.edu.wat.trainingManager.model.TransactionKind;
import pl.edu.wat.trainingManager.repository.DatabaseStatusRepository;
import pl.edu.wat.trainingManager.service.ITransactionService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;


@RestController
public class RestTransactionController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DatabaseStatusRepository databaseStatusRepository;

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(RestTransactionController.class);
    private ITransactionService transactionService;

    @Autowired
    public RestTransactionController(ITransactionService transactionService) {
        Assert.notNull(transactionService, "TransactionService is required");
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> getTransactions() {
        logger.info("Fetching all transactions");
        DatabaseStatus result = entityManager.
                createQuery( "from DatabaseStatus", DatabaseStatus.class ).getSingleResult();
        String isActive = result.getActiveDatabase();
        Boolean result2 = databaseStatusRepository.getDatabaseStatus();

        return transactionService.findAll();
    }

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTransaction(@PathVariable String id) {
       logger.info("Fetching transaction by id, id = " + id);
       return transactionService.findOne(id);
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<?> saveTransaction(@Valid @RequestBody Transaction transaction) {
        logger.info("Processing transaction = " + transaction);
        System.out.println("Zapis transakcji" + transaction);
        return transactionService.save(transaction);
    }

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTransaction(@PathVariable String id) {
        logger.info("Processing delete transaction by id = " + id);
        return transactionService.delete(id);
    }

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        logger.info("Processing update transaction by id = " + id);
        return transactionService.update(id, transaction);
    }

    @RequestMapping(value = "/transactions/monthIncomingBalance", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> getMonthIncomeBalance() {
        System.out.println("tescik");
        return transactionService.getMonthIncomeBalance();
    }

    @RequestMapping(value = "/transactions/monthSpendingBalance", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> getMonthSpendingBalance() {
        System.out.println("tescik");
        return transactionService.getMonthSpendingBalance();
    }
}

package pl.edu.wat.trainingManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wat.trainingManager.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT sum(t.amount) " +
            "FROM Transaction t " +
            "WHERE t.type= ?1 " +
            "AND t.date IS NOT NULL " +
            "AND MONTH(t.date)= ?2 " +
            "AND YEAR(t.date)= ?3 " +
            "GROUP BY MONTH(t.date)")
    String findSumAmountByTypePerMonth(String type, int month, int year);
}

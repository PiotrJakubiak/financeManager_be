package pl.edu.wat.trainingManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wat.trainingManager.model.DatabaseStatus;

/**
 * Created by Piotr on 29.08.2018.
 */
public interface DatabaseStatusRepository extends JpaRepository<DatabaseStatus, Long> {
    @Query("Select activeDatabase FROM DatabaseStatus")
    Boolean getDatabaseStatus();
}

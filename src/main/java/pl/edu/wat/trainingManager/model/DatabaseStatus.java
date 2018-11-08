package pl.edu.wat.trainingManager.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "DatabaseStatus")
public class DatabaseStatus {

    @Id
    @Column(name = "activeDatabase")
    String activeDatabase;

    public String getActiveDatabase() {
        return activeDatabase;
    }

    public void setActiveDatabase(String activeDatabase) {
        this.activeDatabase = activeDatabase;
    }
}

package pl.edu.wat.trainingManager.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "[transaction]")
public class Transaction {

    private long id;

    @NotNull
    @Size(min=1, max=50)
    private String name;

    @NotNull
    private String kind;

    @NotNull
    private String type;

    @NotNull
    private BigDecimal amount;

    private String description;

    private java.util.Date date;

    private TransactionKind transactionKind;

    public Transaction() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) throws ParseException {
        System.out.println("Date:" + date);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.date = date;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionKind_id")
    public TransactionKind getTransactionKind() {
        return transactionKind;
    }

    public void setTransactionKind(TransactionKind transactionKind) {
        this.transactionKind = transactionKind;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

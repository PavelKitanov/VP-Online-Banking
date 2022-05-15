package wp.proekt.ebank.model;

import lombok.Data;
import wp.proekt.ebank.model.enumerations.AccountStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holderName;

    private String holderSurname;

    private Long customerId;

    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private LocalDate dateCreated;

    @OneToMany(mappedBy = "depositAccount")
    private List<Deposit> deposits;

    @OneToMany(mappedBy = "withdrawAccount")
    private List<Withdraw> withdraws;

    @OneToMany(mappedBy = "receiverAccount")
    private List<Transfer> transfersReceived;

    @OneToMany(mappedBy = "senderAccount")
    private List<Transfer> transfersSended;

    public Account() {
    }

    public Account(String holderName, String holderSurname, double balance, Long customerId) {
        this.holderName = holderName;
        this.holderSurname = holderSurname;
        this.balance = balance;
        this.status=AccountStatus.ACTIVE;
        this.dateCreated=LocalDate.now();
        this.customerId=customerId;
        this.deposits=new ArrayList<>();
        this.withdraws=new ArrayList<>();
        this.transfersReceived=new ArrayList<>();
        this.transfersSended=new ArrayList<>();
    }

    public String formatDate(LocalDate dateCreated){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted=this.dateCreated.format(formatter);
        return formatted;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAll(){
        return holderName+" "+holderSurname+" - Account id: "+id+" - Customer id: "+customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderSurname() {
        return holderSurname;
    }

    public void setHolderSurname(String holderSurname) {
        this.holderSurname = holderSurname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(List<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }

    public List<Transfer> getTransfersReceived() {
        return transfersReceived;
    }

    public void setTransfersReceived(List<Transfer> transfersReceived) {
        this.transfersReceived = transfersReceived;
    }

    public List<Transfer> getTransfersSended() {
        return transfersSended;
    }

    public void setTransfersSended(List<Transfer> transfersSended) {
        this.transfersSended = transfersSended;
    }
}

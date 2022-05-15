package wp.proekt.ebank.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Deposit extends Transaction {

    @ManyToOne
    private Account depositAccount;

    private double depositAmount;

    private double beforeDepositAmount;

    private double afterDepositAmount;

    private LocalDate depositDate;

    public Deposit() {
    }

    public Deposit(Account depositAccount, double depositAmount) {
        super();
        this.depositAccount = depositAccount;
        this.depositAmount = depositAmount;
        this.depositDate=LocalDate.now();
        this.beforeDepositAmount=0;
        this.afterDepositAmount=0;
    }

    public String formatDate(LocalDate depositDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted=this.depositDate.format(formatter);
        return formatted;
    }

    public Account getDepositAccount() {
        return depositAccount;
    }

    public void setDepositAccount(Account depositAccount) {
        this.depositAccount = depositAccount;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public double getBeforeDepositAmount() {
        return beforeDepositAmount;
    }

    public void setBeforeDepositAmount(double beforeDepositAmount) {
        this.beforeDepositAmount = beforeDepositAmount;
    }

    public double getAfterDepositAmount() {
        return afterDepositAmount;
    }

    public void setAfterDepositAmount(double afterDepositAmount) {
        this.afterDepositAmount = afterDepositAmount;
    }
}

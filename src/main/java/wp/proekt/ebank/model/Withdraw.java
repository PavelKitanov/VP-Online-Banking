package wp.proekt.ebank.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Withdraw extends Transaction {

    @ManyToOne
    private Account withdrawAccount;

    private double withdrawAmount;

    private double beforeWithdrawAmount;

    private double afterWithdrawAmount;

    private LocalDate withdrawDate;

    public Withdraw() {
    }

    public Withdraw(Account withdrawAccount, double withdrawAmount) {
        super();
        this.withdrawAccount = withdrawAccount;
        this.withdrawAmount = withdrawAmount;
        this.withdrawDate=LocalDate.now();
        this.beforeWithdrawAmount=0;
        this.afterWithdrawAmount=0;
    }

    public String formatDate(LocalDate withdrawDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted=this.withdrawDate.format(formatter);
        return formatted;
    }

    public Account getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(Account withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public double getBeforeWithdrawAmount() {
        return beforeWithdrawAmount;
    }

    public void setBeforeWithdrawAmount(double beforeWithdrawAmount) {
        this.beforeWithdrawAmount = beforeWithdrawAmount;
    }

    public double getAfterWithdrawAmount() {
        return afterWithdrawAmount;
    }

    public void setAfterWithdrawAmount(double afterWithdrawAmount) {
        this.afterWithdrawAmount = afterWithdrawAmount;
    }

    public LocalDate getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(LocalDate withdrawDate) {
        this.withdrawDate = withdrawDate;
    }
}

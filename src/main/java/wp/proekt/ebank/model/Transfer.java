package wp.proekt.ebank.model;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Transfer extends Transaction {

    @ManyToOne
    private Account senderAccount;

    @ManyToOne
    private Account receiverAccount;

    private double transferAmount;

    private LocalDate transferDate;

    public Transfer() {
    }

    public Transfer(Account senderAccount, Account receiverAccount, double transferAmount) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.transferAmount = transferAmount;
        this.transferDate= LocalDate.now();
    }

    public String formatDate(LocalDate transferDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted=this.transferDate.format(formatter);
        return formatted;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }
}

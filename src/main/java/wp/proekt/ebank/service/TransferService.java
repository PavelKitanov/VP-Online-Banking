package wp.proekt.ebank.service;

import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Transfer;

import java.time.LocalDate;
import java.util.List;

public interface TransferService {

    List<Transfer> findAll();
    Transfer findById(Long id);
    Transfer save(Account senderAccount, Account receiverAccount, double transferAmount);
    Transfer save(Transfer transfer);
    void deleteById(Long id);
    List<Transfer> listAllBySenderAndReceiverAndDate(Account senderAcc,Account receiverAcc, LocalDate startDate, LocalDate endDate);
}

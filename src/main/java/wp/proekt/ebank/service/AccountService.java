package wp.proekt.ebank.service;

import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Deposit;
import wp.proekt.ebank.model.Transfer;
import wp.proekt.ebank.model.Withdraw;

import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account findById(Long id);
    Account save(String holderName, String holderSurname, double balance,Long id);
    Account save(Account account);
    void deleteById(Long id);
    Account depositToAccount(Long accoundId,Long depositId);
    Account withdrawFromAccount(Long accountId,Long withdrawId);
    List<Account> transfer(Long senderId, Long receiverId, Long transferId);
    Account cancelDeposit(Deposit deposit);
    Account cancelWithdraw(Withdraw withdraw);
    List<Account> cancelTransfer(Transfer transfer);
    List<Account> listAllByNameAndSurname(String name,String surname);
    Account findByNameAndSurname(String name,String surname);
}

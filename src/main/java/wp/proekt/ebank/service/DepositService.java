package wp.proekt.ebank.service;

import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Deposit;

import java.time.LocalDate;
import java.util.List;

public interface DepositService {


    List<Deposit> findAll();
    Deposit findById(Long id);
    Deposit save(Account depositAccount, double depositAmount);
    Deposit save(Deposit deposit);
    void deleteById(Long id);
    List<Deposit> listAllByDepositDate(LocalDate startDate,LocalDate endDate);
}

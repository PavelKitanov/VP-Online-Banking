package wp.proekt.ebank.service;

import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Withdraw;

import java.time.LocalDate;
import java.util.List;

public interface WithdrawService {

    List<Withdraw> findAll();
    Withdraw findById(Long id);
    Withdraw save(Account withdrawAccount, double withdrawAmount);
    Withdraw save(Withdraw withdraw);
    void deleteById(Long id);
    List<Withdraw> listAllByDate(LocalDate startDate,LocalDate endDate);

}

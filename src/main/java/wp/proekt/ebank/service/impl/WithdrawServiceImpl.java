package wp.proekt.ebank.service.impl;

import org.springframework.stereotype.Service;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Withdraw;
import wp.proekt.ebank.repository.jpa.WithdrawRepository;
import wp.proekt.ebank.service.WithdrawService;

import java.time.LocalDate;
import java.util.List;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    private final WithdrawRepository withdrawRepository;

    public WithdrawServiceImpl(WithdrawRepository withdrawRepository) {
        this.withdrawRepository = withdrawRepository;
    }

    @Override
    public List<Withdraw> findAll() {
        return this.withdrawRepository.findAll();
    }

    @Override
    public Withdraw findById(Long id) {
        return this.withdrawRepository.findById(id).orElse(null);
    }

    @Override
    public Withdraw save(Account withdrawAccount, double withdrawAmount) {
        return this.withdrawRepository.save(new Withdraw(withdrawAccount,withdrawAmount));
    }

    @Override
    public Withdraw save(Withdraw withdraw) {
        return this.withdrawRepository.save(withdraw);
    }

    @Override
    public void deleteById(Long id) {
        this.withdrawRepository.deleteById(id);
    }

    @Override
    public List<Withdraw> listAllByDate(LocalDate startDate, LocalDate endDate) {
        if(startDate!=null && endDate!=null){
            return this.withdrawRepository.findAllByWithdrawDateBetween(startDate,endDate);
        }else if(startDate!=null){
            return this.withdrawRepository.findAllByWithdrawDateAfter(startDate);
        }else if(endDate!=null){
            return this.withdrawRepository.findAllByWithdrawDateBefore(endDate);
        }else {
            return this.withdrawRepository.findAll();
        }
    }
}

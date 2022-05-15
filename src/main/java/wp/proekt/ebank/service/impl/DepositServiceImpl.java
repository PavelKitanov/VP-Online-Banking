package wp.proekt.ebank.service.impl;

import org.springframework.stereotype.Service;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Deposit;
import wp.proekt.ebank.repository.jpa.DepositRepository;
import wp.proekt.ebank.service.DepositService;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;

    public DepositServiceImpl(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public List<Deposit> findAll() {
        return this.depositRepository.findAll();
    }

    @Override
    public Deposit findById(Long id) {
        return this.depositRepository.findById(id).orElse(null);
    }

    @Override
    public Deposit save(Account depositAccount, double depositAmount) {
        return this.depositRepository.save(new Deposit(depositAccount,depositAmount));
    }

    @Override
    public Deposit save(Deposit deposit) {
        return this.depositRepository.save(deposit);
    }

    @Override
    public void deleteById(Long id) {
        this.depositRepository.deleteById(id);
    }

    @Override
    public List<Deposit> listAllByDepositDate(LocalDate startDate, LocalDate endDate){
        if(startDate!=null && endDate!=null){
            return this.depositRepository.findAllByDepositDateBetween(startDate,endDate);
        }
        else if(startDate!=null){
            return this.depositRepository.findAllByDepositDateAfter(startDate);
        }else if(endDate!=null){
            return this.depositRepository.findAllByDepositDateBefore(endDate);
        }else{
            return this.depositRepository.findAll();
        }
    }
}

package wp.proekt.ebank.service.impl;

import org.springframework.stereotype.Service;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Deposit;
import wp.proekt.ebank.model.Transfer;
import wp.proekt.ebank.model.Withdraw;
import wp.proekt.ebank.repository.jpa.AccountRepository;
import wp.proekt.ebank.repository.jpa.DepositRepository;
import wp.proekt.ebank.repository.jpa.TransferRepository;
import wp.proekt.ebank.repository.jpa.WithdrawRepository;
import wp.proekt.ebank.service.AccountService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final DepositRepository depositRepository;
    private final WithdrawRepository withdrawRepository;
    private final TransferRepository transferRepository;

    public AccountServiceImpl(AccountRepository accountRepository, DepositRepository depositRepository, WithdrawRepository withdrawRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
        this.withdrawRepository = withdrawRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return this.accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account save(String holderName, String holderSurname, double balance,Long id) {
        return this.accountRepository.save(new Account(holderName,holderSurname,balance,id));
    }

    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        this.accountRepository.deleteById(id);
    }

    @Override
    public Account depositToAccount(Long accoundId, Long depositId) {
        Account account=this.accountRepository.findById(accoundId).get();
        Deposit deposit=this.depositRepository.findById(depositId).get();
        deposit.setBeforeDepositAmount(account.getBalance());
        deposit.setAfterDepositAmount(account.getBalance()+deposit.getDepositAmount());
        account.getDeposits().add(deposit);
        account.setBalance(account.getBalance()+deposit.getDepositAmount());
        return this.accountRepository.save(account);

    }

    @Override
    public Account withdrawFromAccount(Long accountId, Long withdrawId) {
        Account account=this.accountRepository.findById(accountId).get();
        Withdraw withdraw=this.withdrawRepository.findById(withdrawId).get();
        withdraw.setBeforeWithdrawAmount(account.getBalance());
        withdraw.setAfterWithdrawAmount(account.getBalance()-withdraw.getWithdrawAmount());
        account.getWithdraws().add(withdraw);
        account.setBalance(account.getBalance()-withdraw.getWithdrawAmount());
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> transfer(Long senderId, Long receiverId, Long transferId) {
        Account senderAccount=this.accountRepository.findById(senderId).get();
        Account receiverAccount=this.accountRepository.findById(receiverId).get();
        Transfer transfer=this.transferRepository.findById(transferId).get();
        senderAccount.getTransfersSended().add(transfer);
        receiverAccount.getTransfersReceived().add(transfer);
        senderAccount.setBalance(senderAccount.getBalance()-transfer.getTransferAmount());
        receiverAccount.setBalance(receiverAccount.getBalance()+transfer.getTransferAmount());

        List<Account> accounts=new ArrayList<>();
        accounts.add(senderAccount);
        accounts.add(receiverAccount);

        return this.accountRepository.saveAll(accounts);
    }

    @Override
    public Account cancelDeposit(Deposit deposit) {
       Account account=deposit.getDepositAccount();
       account.getDeposits().remove(deposit);
       account.setBalance(account.getBalance()-deposit.getDepositAmount());
       return this.accountRepository.save(account);
    }

    @Override
    public Account cancelWithdraw(Withdraw withdraw) {
        Account account=withdraw.getWithdrawAccount();
        account.getWithdraws().remove(withdraw);
        account.setBalance(account.getBalance()+withdraw.getWithdrawAmount());
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> cancelTransfer(Transfer transfer) {
        Account senderAccount=transfer.getSenderAccount();
        Account receiverAccount=transfer.getReceiverAccount();
        senderAccount.getTransfersSended().remove(transfer);
        receiverAccount.getTransfersReceived().remove(transfer);
        senderAccount.setBalance(senderAccount.getBalance()+transfer.getTransferAmount());
        receiverAccount.setBalance(receiverAccount.getBalance()-transfer.getTransferAmount());

        List<Account> accounts=new ArrayList<>();
        accounts.add(senderAccount);
        accounts.add(receiverAccount);

        return this.accountRepository.saveAll(accounts);
    }

    @Override
    public List<Account> listAllByNameAndSurname(String name, String surname) {
        String nameLike="%"+name+"%";
        String surnameLike="%"+surname+"%";
        if(name!=null && surname!=null){
            return this.accountRepository.findAllByHolderNameLikeAndHolderSurnameLike(nameLike,surnameLike);
        }else if(name!=null){
            return this.accountRepository.findAllByHolderNameLike(nameLike);
        }
        else if(surname!=null){
            return this.accountRepository.findAllByHolderSurnameLike(surnameLike);
        }else{
            return this.accountRepository.findAll();
        }
    }

    @Override
    public Account findByNameAndSurname(String name, String surname) {
        if(name!=null && surname!=null){
            return this.accountRepository.findByHolderNameLikeAndHolderSurnameLike(name,surname);
        }else if(name!=null){
            return this.accountRepository.findByHolderNameLike(name);
        }else if(surname!=null){
            return this.accountRepository.findByHolderSurnameLike(surname);
        }else{
            return null;
        }
    }
}

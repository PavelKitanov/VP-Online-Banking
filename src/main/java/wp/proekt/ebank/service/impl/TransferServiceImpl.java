package wp.proekt.ebank.service.impl;

import org.springframework.stereotype.Service;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Transfer;
import wp.proekt.ebank.repository.jpa.TransferRepository;
import wp.proekt.ebank.service.TransferService;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Transfer> findAll() {
        return this.transferRepository.findAll();
    }

    @Override
    public Transfer findById(Long id) {
        return this.transferRepository.findById(id).orElse(null);
    }

    @Override
    public Transfer save(Account senderAccount, Account receiverAccount, double transferAmount) {
        return this.transferRepository.save(new Transfer(senderAccount,receiverAccount,transferAmount));
    }

    @Override
    public Transfer save(Transfer transfer) {
        return this.transferRepository.save(transfer);
    }

    @Override
    public void deleteById(Long id) {
        this.transferRepository.deleteById(id);
    }

    @Override
    public List<Transfer> listAllBySenderAndReceiverAndDate(Account senderAcc, Account receiverAcc, LocalDate startDate, LocalDate endDate) {
        if (senderAcc != null && receiverAcc != null && startDate != null && endDate != null) {
            return this.transferRepository.findAllBySenderAccountAndReceiverAccountAndTransferDateBetween(senderAcc,receiverAcc, startDate, endDate);
        }else if(senderAcc != null && receiverAcc != null && startDate != null){
            return this.transferRepository.findAllBySenderAccountAndReceiverAccountAndTransferDateAfter(senderAcc,receiverAcc, startDate);
        }else if(senderAcc != null && receiverAcc != null && endDate != null){
            return this.transferRepository.findAllBySenderAccountAndReceiverAccountAndTransferDateBefore(senderAcc,receiverAcc, endDate);
        } else if (senderAcc != null && receiverAcc != null) {
            return this.transferRepository.findAllBySenderAccountAndReceiverAccount(senderAcc,receiverAcc);
        } else if (senderAcc!=null && startDate != null && endDate != null) {
            return this.transferRepository.findAllBySenderAccountAndTransferDateBetween(senderAcc, startDate, endDate);
        }else if(senderAcc!=null && startDate != null){
            return this.transferRepository.findAllBySenderAccountAndTransferDateAfter(senderAcc,startDate);
        }else if(senderAcc!=null && endDate != null) {
            return this.transferRepository.findAllBySenderAccountAndTransferDateBefore(senderAcc,endDate);
        } else if (receiverAcc!=null && startDate != null && endDate != null) {
            return this.transferRepository.findAllByReceiverAccountAndTransferDateBetween(receiverAcc, startDate, endDate);
        }else if(receiverAcc != null && startDate != null){
            return this.transferRepository.findAllByReceiverAccountAndTransferDateAfter(receiverAcc, startDate);
        }else if(receiverAcc != null && endDate != null){
            return this.transferRepository.findAllByReceiverAccountAndTransferDateBefore(receiverAcc, endDate);
        } else if(senderAcc!=null){
            return this.transferRepository.findAllBySenderAccount(senderAcc);
        }else if(receiverAcc!=null){
            return this.transferRepository.findAllByReceiverAccount(receiverAcc);
        }else if(startDate!=null && endDate!=null){
            return this.transferRepository.findAllByTransferDateBetween(startDate,endDate);
        }else if(startDate!=null){
            return this.transferRepository.findAllByTransferDateAfter(startDate);
        }else if(endDate!=null){
            return this.transferRepository.findAllByTransferDateBefore(endDate);
        }else{
            return this.transferRepository.findAll();
        }
    }

}

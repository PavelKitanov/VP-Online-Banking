package wp.proekt.ebank.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Transfer;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {

    List<Transfer> findAllBySenderAccountAndReceiverAccountAndTransferDateBetween(Account senderAcc,Account receiverAcc,LocalDate startDate,LocalDate endDate);
    List<Transfer> findAllBySenderAccountAndReceiverAccountAndTransferDateAfter(Account senderAcc,Account receiverAcc, LocalDate startDate);
    List<Transfer> findAllBySenderAccountAndReceiverAccountAndTransferDateBefore(Account senderAcc,Account receiverAcc, LocalDate endDate);
    List<Transfer> findAllBySenderAccountAndReceiverAccount(Account senderAcc,Account receiverAcc);
    List<Transfer> findAllBySenderAccount(Account senderAcc);
    List<Transfer> findAllBySenderAccountAndTransferDateBetween(Account senderAcc,LocalDate startDate,LocalDate endDate);
    List<Transfer> findAllBySenderAccountAndTransferDateAfter(Account senderAcc,LocalDate startDate);
    List<Transfer> findAllBySenderAccountAndTransferDateBefore(Account senderAcc,LocalDate endDate);
    List<Transfer> findAllByReceiverAccount(Account receiverAcc);
    List<Transfer> findAllByReceiverAccountAndTransferDateBetween(Account receiverAcc,LocalDate startDate,LocalDate endDate);
    List<Transfer> findAllByReceiverAccountAndTransferDateAfter(Account receiverAcc,LocalDate startDate);
    List<Transfer> findAllByReceiverAccountAndTransferDateBefore(Account receiverAcc,LocalDate endDate);
    List<Transfer> findAllByTransferDateBetween(LocalDate startDate,LocalDate endDate);
    List<Transfer> findAllByTransferDateAfter(LocalDate startDate);
    List<Transfer> findAllByTransferDateBefore(LocalDate endDate);


}

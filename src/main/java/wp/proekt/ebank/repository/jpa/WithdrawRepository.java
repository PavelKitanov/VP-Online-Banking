package wp.proekt.ebank.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.proekt.ebank.model.Withdraw;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw,Long> {
    List<Withdraw> findAllByWithdrawDateBetween(LocalDate startDate,LocalDate endDate);
    List<Withdraw> findAllByWithdrawDateAfter(LocalDate startDate);
    List<Withdraw> findAllByWithdrawDateBefore(LocalDate endDate);
}

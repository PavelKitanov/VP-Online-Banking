package wp.proekt.ebank.repository.jpa;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.proekt.ebank.model.Deposit;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {
    List<Deposit> findAllByDepositDateBetween(LocalDate startDate,LocalDate endDate);
    List<Deposit> findAllByDepositDateAfter(LocalDate startDate);
    List<Deposit> findAllByDepositDateBefore(LocalDate endDate);
}

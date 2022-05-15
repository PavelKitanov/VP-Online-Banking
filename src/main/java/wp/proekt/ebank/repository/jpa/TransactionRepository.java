package wp.proekt.ebank.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.proekt.ebank.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}

package wp.proekt.ebank.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.proekt.ebank.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAllByHolderNameLikeAndHolderSurnameLike(String name, String surname);
    List<Account> findAllByHolderNameLike(String name);
    List<Account> findAllByHolderSurnameLike(String surname);
    Account findByHolderNameLikeAndHolderSurnameLike(String name,String surname);
    Account findByHolderNameLike(String name);
    Account findByHolderSurnameLike(String surname);

}

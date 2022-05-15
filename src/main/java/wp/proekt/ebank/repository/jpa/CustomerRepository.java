package wp.proekt.ebank.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wp.proekt.ebank.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByCustomerNameAndCustomerSurname(String name,String surname);
    Customer findByEmail(String email);
    List<Customer> findAllByCustomerNameLikeAndCustomerSurnameLike(String name,String surname);
    List<Customer> findAllByCustomerNameLike(String name);
    List<Customer> findAllByCustomerSurnameLike(String surname);
}

package wp.proekt.ebank.service;

import org.springframework.stereotype.Service;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Customer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer findByEmail(String email);
    Customer findByNameAndSurname(String name,String surname);
    Customer save(String customerName, String customerSurname, String address, String gender, String email, LocalDate dateOfBirth, String phoneNumber);
    Customer edit(Long id,String customerName, String customerSurname, String address, String gender, String email, LocalDate dateOfBirth, String phoneNumber);
    Customer addAccountToCustomer(Long customerId, Long accountId);
    void deleteById(Long id);
    List<Customer> listAllByNameAndSurname(String name,String surname);
    Customer removeAccount(Long accountId);





}

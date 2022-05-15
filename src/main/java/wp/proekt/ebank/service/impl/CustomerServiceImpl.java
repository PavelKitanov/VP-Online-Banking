package wp.proekt.ebank.service.impl;

import org.springframework.stereotype.Service;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Customer;
import wp.proekt.ebank.repository.jpa.AccountRepository;
import wp.proekt.ebank.repository.jpa.CustomerRepository;
import wp.proekt.ebank.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findByEmail(String email) {
        return this.customerRepository.findByEmail(email);
    }

    @Override
    public Customer findByNameAndSurname(String name, String surname) {
        return this.customerRepository.findByCustomerNameAndCustomerSurname(name,surname);
    }

    @Override
    public Customer save(String customerName, String customerSurname, String address, String gender, String email, LocalDate dateOfBirth, String phoneNumber) {
        return this.customerRepository.save(new Customer(customerName,customerSurname,address,gender,email,dateOfBirth,phoneNumber));
    }

    @Override
    public Customer edit(Long id, String customerName, String customerSurname, String address, String gender, String email, LocalDate dateOfBirth, String phoneNumber) {
        Customer customer=this.customerRepository.findById(id).get();
        customer.setCustomerName(customerName);
        customer.setCustomerSurname(customerSurname);
        customer.setAddress(address);
        customer.setGender(gender);
        customer.setEmail(email);
        customer.setDateOfBirth(dateOfBirth);
        customer.setPhoneNumber(phoneNumber);
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer addAccountToCustomer(Long customerId, Long accountId) {
        Customer customer=this.customerRepository.findById(customerId).get();
        Account account=this.accountRepository.findById(accountId).get();
        customer.getAccounts().add(account);
        return this.customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> listAllByNameAndSurname(String name, String surname) {
        String nameLike="%"+name+"%";
        String surnameLike="%"+surname+"%";
        if(name!=null && surname!=null){
            return this.customerRepository.findAllByCustomerNameLikeAndCustomerSurnameLike(nameLike,surnameLike);
        }
        else if(name!=null){
            return this.customerRepository.findAllByCustomerNameLike(nameLike);
        }
        else if(surname!=null){
            return this.customerRepository.findAllByCustomerSurnameLike(surnameLike);
        }
        else{
            return this.customerRepository.findAll();
        }
    }

    @Override
    public Customer removeAccount(Long accountId) {
        Account account=this.accountRepository.findById(accountId).get();
        Customer customer=this.customerRepository.findById(account.getCustomerId()).get();
        customer.getAccounts().remove(account);
        return this.customerRepository.save(customer);
    }
}

package wp.proekt.ebank.model;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String customerSurname;

    private String address;

    private String gender;

    private String email;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    @ManyToMany
    private List<Account> accounts;

    public Customer() {
    }


    public Customer(String customerName, String customerSurname, String address, String gender, String email, LocalDate dateOfBirth, String phoneNumber) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.accounts=new ArrayList<>();
    }

    public String formatDate(LocalDate dateOfBirth){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatted=this.dateOfBirth.format(formatter);
        return formatted;
    }

    public Customer addAccount(Account account){
        this.accounts.add(account);
        return this;
    }

    public String getAll(){
        return "Id: "+id.toString()+" - "+"Name: "+customerName+" - Surname: "+customerSurname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

package wp.proekt.ebank.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.proekt.ebank.model.Customer;
import wp.proekt.ebank.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomerPage(@RequestParam(required = false) String name,@RequestParam(required = false) String surname, Model model){
        List<Customer> customerList;
        if(name==null && surname==null){
            customerList=this.customerService.findAll();
        }else{
            customerList=this.customerService.listAllByNameAndSurname(name,surname);
        }
        model.addAttribute("customers",customerList);
        model.addAttribute("bodyContent","customers");
        return "master-template";
    }

    @GetMapping("/add")
    public String addCustomer(Model model){
        model.addAttribute("bodyContent","add-customer");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveCustomer(@RequestParam Long customerId,
                               @RequestParam String name,
                               @RequestParam String lastname,
                               @RequestParam String address,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                               @RequestParam String gender,
                               @RequestParam String phone,
                               @RequestParam String email){

        if(customerId==-1){
            this.customerService.save(name,lastname,address,gender,email,dob,phone);
        }
        else{
            this.customerService.edit(customerId,name,lastname,address,gender,email,dob,phone);
        }

        return "redirect:/customers";
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id,Model model){
        Customer customer=this.customerService.findById(id);
        model.addAttribute("customer",customer);
        model.addAttribute("bodyContent","add-customer");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){
        this.customerService.deleteById(id);
        return "redirect:/customers";
    }

}

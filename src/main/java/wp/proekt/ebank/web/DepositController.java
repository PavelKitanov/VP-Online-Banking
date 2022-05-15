package wp.proekt.ebank.web;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Customer;
import wp.proekt.ebank.model.Deposit;
import wp.proekt.ebank.service.AccountService;
import wp.proekt.ebank.service.CustomerService;
import wp.proekt.ebank.service.DepositService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/deposits")
public class DepositController {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final DepositService depositService;

    public DepositController(CustomerService customerService, AccountService accountService, DepositService depositService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.depositService = depositService;
    }

    @GetMapping
    public String finaAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate,
                          Model model){
        List<Deposit> deposits;
        if(fromdate==null && todate==null){
            deposits=this.depositService.findAll();
        }else {
            deposits=this.depositService.listAllByDepositDate(fromdate,todate);
        }
        model.addAttribute("deposits",deposits);
        model.addAttribute("bodyContent","deposits");
        return "master-template";
    }


    @PostMapping("/deposit/{id}")
    public String addDeposit(@PathVariable Long id, Model model){
        List<Account> accounts=this.accountService.findAll();
        Account account=this.accountService.findById(id);
        model.addAttribute("account",account);
        model.addAttribute("accounts",accounts);
        model.addAttribute("bodyContent","add-deposit");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String cancelDeposit(@PathVariable Long id){
        Deposit deposit=this.depositService.findById(id);
        this.accountService.cancelDeposit(deposit);
        this.depositService.deleteById(id);
        return "redirect:/deposits";
    }
}

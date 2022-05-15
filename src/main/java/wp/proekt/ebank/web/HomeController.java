package wp.proekt.ebank.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.service.*;

import java.util.List;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final TransferService transferService;

    public HomeController(CustomerService customerService, AccountService accountService, DepositService depositService, WithdrawService withdrawService, TransferService transferService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.transferService = transferService;
    }

    @GetMapping
    public String getHomePage(Model model){
        Long customers=this.customerService.findAll().stream().count();
        Long accounts=this.accountService.findAll().stream().count();
        Long deposits=this.depositService.findAll().stream().count();
        Long withdraws=this.withdrawService.findAll().stream().count();
        Long transfers=this.transferService.findAll().stream().count();


        model.addAttribute("customers",customers);
        model.addAttribute("accounts",accounts);
        model.addAttribute("transactions",deposits+withdraws+transfers);
        model.addAttribute("bodyContent","home");
        return "master-template";
    }
}

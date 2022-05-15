package wp.proekt.ebank.web;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Deposit;
import wp.proekt.ebank.model.Withdraw;
import wp.proekt.ebank.service.AccountService;
import wp.proekt.ebank.service.WithdrawService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/withdraws")
public class WithdrawController {

    private final WithdrawService withdrawService;
    private final AccountService accountService;

    public WithdrawController(WithdrawService withdrawService, AccountService accountService) {
        this.withdrawService = withdrawService;
        this.accountService = accountService;
    }

    @GetMapping
    public String findAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate,
                          Model model){
        List<Withdraw> withdraws;
        if(fromdate==null && todate==null){
            withdraws=this.withdrawService.findAll();
        }else{
            withdraws=this.withdrawService.listAllByDate(fromdate,todate);
        }
        model.addAttribute("withdraws",withdraws);
        model.addAttribute("bodyContent","withdraws");
        return "master-template";
    }

    @PostMapping("/withdraw/{id}")
    public String addWithdraw(@PathVariable Long id, Model model){
        List<Account> accounts=this.accountService.findAll();
        Account account=this.accountService.findById(id);
        model.addAttribute("account",account);
        model.addAttribute("accounts",accounts);
        model.addAttribute("bodyContent","add-withdraw");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String cancelDeposit(@PathVariable Long id){
        Withdraw withdraw=this.withdrawService.findById(id);
        this.accountService.cancelWithdraw(withdraw);
        this.withdrawService.deleteById(id);
        return "redirect:/withdraws";
    }
}

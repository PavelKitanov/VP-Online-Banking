package wp.proekt.ebank.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.proekt.ebank.model.*;
import wp.proekt.ebank.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final TransferService transferService;

    public AccountController(AccountService accountService, CustomerService customerService, DepositService depositService, WithdrawService withdrawService, TransferService transferService) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.transferService = transferService;
    }

    @GetMapping
    public String getAccountPage(@RequestParam(required = false) String name,@RequestParam(required = false) String surname,Model model){
        List<Account> accounts;
        if(name==null && surname==null){
            accounts=this.accountService.findAll();
        }else{
            accounts=this.accountService.listAllByNameAndSurname(name,surname);
        }
        model.addAttribute("accounts",accounts);
        model.addAttribute("bodyContent","accounts");
        return "master-template";
    }
    @GetMapping("/{id}")
    public String getAccount(@PathVariable Long id,Model model){
        Account account=this.accountService.findById(id);
        model.addAttribute("account",account);

        return "";
    }

    @PostMapping("/create")
    public String createAccount(@RequestParam Long customerId,@RequestParam double balance,Model model){
        if(balance<0){
            List<Customer> customers=this.customerService.findAll();
            Boolean flag=true;
            model.addAttribute("flag",flag);
            model.addAttribute("customers",customers);
            model.addAttribute("bodyContent","add-account");
            return "master-template";
        }
        Customer customer=this.customerService.findById(customerId);
        Account account=new Account(customer.getCustomerName(),customer.getCustomerSurname(),balance,customerId);
        this.accountService.save(account);
        this.customerService.addAccountToCustomer(customerId, account.getId());

        return "redirect:/accounts/create";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id){
        Account account=this.accountService.findById(id);
        this.customerService.removeAccount(id);
        this.accountService.deleteById(id);

        return "redirect:/accounts/customeraccount/"+account.getCustomerId();
    }

    @GetMapping("/customeraccount/{id}")
    public String getCustomerAccounts(@PathVariable Long id,Model model){
        Customer customer=this.customerService.findById(id);
        List<Account> accounts=customer.getAccounts();
        model.addAttribute("accounts",accounts);
        model.addAttribute("bodyContent","customer-account");
        return "master-template";
    }

    @GetMapping("/create")
    public String createAccount(Model model){
        List<Customer> customers=this.customerService.findAll();
        model.addAttribute("customers",customers);
        model.addAttribute("bodyContent","add-account");
        return "master-template";
    }

    @PostMapping("/deposit")
    public String depositToAccount(@RequestParam Long accountId,@RequestParam double amount,Model model){
        Account account=this.accountService.findById(accountId);
        if(amount<=0){
            Boolean flag=true;
            List<Account> accounts=this.accountService.findAll();
            model.addAttribute("account",account);
            model.addAttribute("accounts",accounts);
            model.addAttribute("flag",flag);
            model.addAttribute("bodyContent","add-deposit");
            return "master-template";
        }
        Deposit deposit=new Deposit(account,amount);
        this.depositService.save(deposit);
        this.accountService.depositToAccount(accountId,deposit.getId());

        return "redirect:/accounts/customeraccount/"+account.getCustomerId();

    }

    @PostMapping("/withdraw")
    public String withdrawFromAccount(@RequestParam Long accountId,@RequestParam double amount,Model model){
        Account account=this.accountService.findById(accountId);
        if(amount<=0 || amount>account.getBalance()){
            Boolean flag;
            if(amount<=0){
                flag=false;
            }else{
                flag=true;
            }
            List<Account> accounts=this.accountService.findAll();
            model.addAttribute("account",account);
            model.addAttribute("accounts",accounts);
            model.addAttribute("flag",flag);
            model.addAttribute("bodyContent","add-withdraw");
            return "master-template";
        }
        Withdraw withdraw=new Withdraw(account,amount);
        this.withdrawService.save(withdraw);
        this.accountService.withdrawFromAccount(accountId,withdraw.getId());

        return "redirect:/accounts/customeraccount/"+account.getCustomerId();
    }

    @PostMapping("/transfer")
    public String Transfer(@RequestParam Long senderId,@RequestParam Long receiverId,@RequestParam double amount,Model model){
        Account senderAccount=this.accountService.findById(senderId);
        if(amount<=0 || amount>senderAccount.getBalance() || (senderId==receiverId)){
            Boolean flag1=false;
            Boolean flag2=false;
            Boolean flag3=false;
            if(amount<=0){
                flag1=true;
            }else if(amount>senderAccount.getBalance()){
                flag2=true;
            }else{
                flag3=true;
            }
            List<Account> accounts=this.accountService.findAll();
            model.addAttribute("accounts",accounts);
            model.addAttribute("flag1",flag1);
            model.addAttribute("flag2",flag2);
            model.addAttribute("flag3",flag3);
            model.addAttribute("bodyContent","add-transfer");
            return "master-template";
        }
        Account receiverAccount=this.accountService.findById(receiverId);
        Transfer transfer=new Transfer(senderAccount,receiverAccount,amount);
        this.transferService.save(transfer);
        this.accountService.transfer(senderId,receiverId, transfer.getId());

        return "redirect:/accounts";
    }

    @GetMapping("/deposithistory/{id}")
    public String depositHistory(@PathVariable Long id,Model model){
        Account account=this.accountService.findById(id);
        List<Deposit> deposits=account.getDeposits();
        model.addAttribute("deposits",deposits);
        model.addAttribute("bodyContent","deposits");
        return "master-template";
    }

    @GetMapping("/withdrawhistory/{id}")
    public String withdrawHistory(@PathVariable Long id,Model model){
        Account account=this.accountService.findById(id);
        List<Withdraw> withdraws=account.getWithdraws();
        model.addAttribute("withdraws",withdraws);
        model.addAttribute("bodyContent","withdraws");
        return "master-template";
    }

    @GetMapping("/transfershistory/{id}")
    public String transferHistory(@PathVariable Long id,Model model){
        Account account=this.accountService.findById(id);
        List<Transfer> transfers=new ArrayList<>();
        Stream.of(account.getTransfersSended(),account.getTransfersReceived()).forEach(transfers::addAll);
        model.addAttribute("transfers",transfers);
        model.addAttribute("bodyContent","transfers");
        return "master-template";
    }
}

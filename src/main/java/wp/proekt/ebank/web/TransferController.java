package wp.proekt.ebank.web;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.proekt.ebank.model.Account;
import wp.proekt.ebank.model.Transfer;
import wp.proekt.ebank.model.Withdraw;
import wp.proekt.ebank.service.AccountService;
import wp.proekt.ebank.service.TransferService;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;
    private final AccountService accountService;

    public TransferController(TransferService transferService, AccountService accountService) {
        this.transferService = transferService;
        this.accountService = accountService;
    }

    @GetMapping
    public String findAll(@RequestParam(required = false) String senderName,
                          @RequestParam(required = false) String senderSurname,
                          @RequestParam(required = false) String receiverName,
                          @RequestParam(required = false) String receiverSurname,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate,
                          Model model){

        List<Transfer> transfers;
        if(senderName==null && senderSurname==null && receiverName==null && receiverSurname==null && fromdate==null && todate==null){
            transfers=this.transferService.findAll();
        }else{
            Account senderAccounts=this.accountService.findByNameAndSurname(senderName,senderSurname);
            Account receiverAccounts=this.accountService.findByNameAndSurname(receiverName,receiverSurname);
            transfers=this.transferService.listAllBySenderAndReceiverAndDate(senderAccounts,receiverAccounts,fromdate,todate);
        }
        model.addAttribute("transfers",transfers);
        model.addAttribute("bodyContent","transfers");
        return "master-template";
    }

    @GetMapping("/addtransfer")
    public String addTransfer(Model model){
        List<Account> accounts=this.accountService.findAll();
        model.addAttribute("accounts",accounts);
        model.addAttribute("bodyContent","add-transfer");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String cancelDeposit(@PathVariable Long id){
        Transfer transfer=this.transferService.findById(id);
        this.accountService.cancelTransfer(transfer);
        this.transferService.deleteById(id);
        return "redirect:/transfers";
    }
}

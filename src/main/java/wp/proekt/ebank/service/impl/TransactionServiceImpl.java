package wp.proekt.ebank.service.impl;

import wp.proekt.ebank.model.Transaction;
import wp.proekt.ebank.repository.jpa.TransactionRepository;
import wp.proekt.ebank.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<Transaction> findAll() {
        return null;
    }
}

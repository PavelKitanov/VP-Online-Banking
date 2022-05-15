package wp.proekt.ebank.service;

import wp.proekt.ebank.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
}

package com.tiagobarbosa.springbatchtests.processors;

import com.tiagobarbosa.springbatchtests.domain.Transaction;
import org.springframework.batch.item.ItemProcessor;

public class TransactionProcessor implements ItemProcessor<Transaction, Transaction> {
    @Override
    public Transaction process(Transaction transaction) throws Exception {
        System.out.printf("%nApplying business rules on Transaction: %s%n", transaction.getDescription());
        return transaction;
    }
}

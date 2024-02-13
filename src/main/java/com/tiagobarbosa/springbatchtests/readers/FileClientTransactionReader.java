package com.tiagobarbosa.springbatchtests.readers;

import com.tiagobarbosa.springbatchtests.domain.Client;
import com.tiagobarbosa.springbatchtests.domain.Transaction;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class FileClientTransactionReader implements ItemStreamReader<Client>, ResourceAwareItemReaderItemStream<Client> {
    private Object currentObject;
    private final FlatFileItemReader<Object> delegate;

    public FileClientTransactionReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Client read() throws Exception {
        if (currentObject == null)
            currentObject = delegate.read();

        Client client = (Client) currentObject;
        currentObject = null;

        if (client != null) {
            while (peek() instanceof Transaction)
                client.getTransactions().add((Transaction) currentObject);
        }

        return client;
    }

    private Object peek() throws Exception {
        currentObject = delegate.read();
        return currentObject;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}

package com.tiagobarbosa.springbatchtests.processors;

import com.tiagobarbosa.springbatchtests.domain.Client;
import org.springframework.batch.item.ItemProcessor;

public class ClientProcessor implements ItemProcessor<Client, Client> {
    @Override
    public Client process(Client client) throws Exception {
        System.out.printf("%nApplying business rules on Client: %s%n", client.getEmail());
        return client;
    }
}

package org.wsd.app;

import lombok.extern.log4j.Log4j2;
import org.eventa.core.dispatcher.impl.CommandDispatcherImpl;
import org.eventa.core.eventstore.EventModel;
import org.eventa.core.repository.EventModelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wsd.app.commands.CreateAccountCommand;
import org.wsd.app.commands.UpdateAccountCommand;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@SpringBootTest
class ApplicationTests {


    @Autowired
    private CommandDispatcherImpl commandDispatcher;

    @Test
    void contextLoads() throws Exception {



        UUID accountId = UUID.randomUUID();

        CreateAccountCommand createAccountCommand = CreateAccountCommand.builder()
                .accountId(accountId)
                .accountName("Partha Sutradhar")
                .accountType("SALARY")
                .balance(12000d)
                .build();

        try {
            this.commandDispatcher.dispatch(createAccountCommand, ((commandMessage, commandResultMessage) -> {
                if (commandResultMessage.isExceptional()) {
                    log.error(commandResultMessage.getException().getMessage());
                } else {
                    log.info(commandResultMessage.getResult());
                }
            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final Instant start = Instant.now();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                UpdateAccountCommand updateAccountCommand = UpdateAccountCommand.builder()
                        .accountId(accountId)
                        .accountName("Partha Sutradhar")
                        .accountType("SALARY")
                        .balance(12000d)
                        .build();
                try {
                    this.commandDispatcher.dispatch(updateAccountCommand, ((commandMessage, commandResultMessage) -> {
                        if (commandResultMessage.isExceptional()) {
                            log.error(commandResultMessage.getException().getMessage());
                        } else {
                            log.info(commandResultMessage.getResult());
                        }
                    }));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }

        final Instant end = Instant.now();

        System.out.println("Duration : " + Duration.between(start, end).toMillis());
        System.out.println("Finished");
    }

}

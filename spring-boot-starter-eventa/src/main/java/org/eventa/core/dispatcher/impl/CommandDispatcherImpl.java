package org.eventa.core.bus.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eventa.core.bus.CommandDispatcher;
import org.eventa.core.commands.BaseCommand;
import org.eventa.core.commands.CommandMessage;
import org.eventa.core.commands.CommandResultMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.BiConsumer;

@Log4j2
@Component
@RequiredArgsConstructor
public class CommandDispatcherImpl implements CommandDispatcher {

    @Override
    @Transactional(transactionManager = "kafkaTransactionManager", rollbackFor = Exception.class)
    public <T extends BaseCommand> void send(T baseCommand, BiConsumer<CommandMessage<T>, CommandResultMessage<?>> callback) throws Exception {

    }
}

package org.eventa.core.bus;

import org.eventa.core.commands.BaseCommand;
import org.eventa.core.commands.CommandMessage;
import org.eventa.core.commands.CommandResultMessage;

import java.util.function.BiConsumer;

public interface CommandDispatcher {
    <T extends BaseCommand> void send(T baseCommand, BiConsumer<CommandMessage<T>, CommandResultMessage<?>> callback) throws Exception;
}

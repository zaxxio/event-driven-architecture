package org.eventa.core.interceptor;

import java.util.concurrent.CopyOnWriteArrayList;

public class CommandInterceptorRegisterer {
    private CopyOnWriteArrayList<CommandInterceptor> commandInterceptors;

    public CommandInterceptorRegisterer() {
        this.commandInterceptors = new CopyOnWriteArrayList<>();
    }

    public void register(CommandInterceptor commandInterceptor) {
        this.commandInterceptors.add(commandInterceptor);
    }

    public CopyOnWriteArrayList<CommandInterceptor> getCommandInterceptors() {
        return commandInterceptors;
    }
}

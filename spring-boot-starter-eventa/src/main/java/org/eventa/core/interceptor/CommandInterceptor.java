package org.eventa.core.interceptor;

import org.eventa.core.commands.BaseCommand;

public interface BaseCommandInterceptor {
    void preHandle(BaseCommand baseCommand) throws Exception;

    void postHandle(BaseCommand baseCommand) throws Exception;
}

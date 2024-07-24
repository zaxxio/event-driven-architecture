package org.wsd.app.interceptor;

import lombok.extern.log4j.Log4j2;
import org.eventa.core.commands.BaseCommand;
import org.eventa.core.interceptor.BaseCommandInterceptor;
import org.eventa.core.streotype.CommandInterceptor;
import org.wsd.app.commands.CreateAccountCommand;

@Log4j2
@CommandInterceptor
public class AccountBaseCommandInterceptor implements BaseCommandInterceptor {

    @Override
    public void preHandle(BaseCommand baseCommand) throws Exception {
        if (baseCommand instanceof CreateAccountCommand) {
            log.info("Pre CreateAccountCommand Intercepted.");
        }
    }

    @Override
    public void postHandle(BaseCommand baseCommand) throws Exception {
        if (baseCommand instanceof CreateAccountCommand) {
            log.info("Pre CreateAccountCommand Intercepted.");
        }
    }
}

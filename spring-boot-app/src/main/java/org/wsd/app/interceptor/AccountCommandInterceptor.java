package org.wsd.app.interceptor;

import lombok.extern.log4j.Log4j2;
import org.eventa.core.commands.BaseCommand;
import org.eventa.core.interceptor.CommandInterceptor;
import org.eventa.core.streotype.Interceptor;
import org.springframework.stereotype.Service;
import org.wsd.app.commands.UpdateAccountCommand;

@Log4j2
@Service
@Interceptor
public class AccountCommandInterceptor implements CommandInterceptor {
    @Override
    public void commandIntercept(BaseCommand baseCommand) throws Exception {
        if (baseCommand instanceof UpdateAccountCommand) {
            log.info("Pre CreateAccountCommand Intercepted.");
        }
    }
}

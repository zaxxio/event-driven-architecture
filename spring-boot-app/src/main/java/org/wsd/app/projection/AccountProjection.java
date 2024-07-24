package org.wsd.app.projection;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eventa.core.streotype.EventHandler;
import org.eventa.core.streotype.ProjectionGroup;
import org.eventa.core.streotype.QueryHandler;
import org.eventa.core.streotype.ResetHandler;
import org.springframework.stereotype.Service;
import org.wsd.app.entity.AccountEntity;
import org.wsd.app.events.AccountCreatedEvent;
import org.wsd.app.events.AccountDeletedEvent;
import org.wsd.app.events.AccountUpdatedEvent;
import org.wsd.app.query.FindAllAccountsQuery;
import org.wsd.app.query.FindByAccountIdQuery;
import org.wsd.app.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@ProjectionGroup(name = "account-group")
public class AccountProjection {

    private final AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountCreatedEvent.getAccountId());
        accountEntity.setEmail(accountCreatedEvent.getEmail());
        accountEntity.setAccountType(accountCreatedEvent.getAccountType());
        accountEntity.setAccountName(accountCreatedEvent.getAccountName());
        accountEntity.setBalance(accountCreatedEvent.getBalance());
        accountRepository.save(accountEntity);
        log.info("Account Created {}", accountCreatedEvent);
        printThreadId();
    }

    @EventHandler
    public void on(AccountUpdatedEvent accountUpdatedEvent) {
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountUpdatedEvent.getAccountId());
        if (optionalAccountEntity.isPresent()) {
            AccountEntity accountEntity = optionalAccountEntity.get();
            accountEntity.setEmail(accountUpdatedEvent.getEmail());
            accountEntity.setAccountType(accountUpdatedEvent.getAccountType());
            accountEntity.setAccountName(accountUpdatedEvent.getAccountName());
            accountEntity.setBalance(accountUpdatedEvent.getBalance());
            this.accountRepository.save(accountEntity);
        }
        log.info("Account Updated {}", accountUpdatedEvent);
        printThreadId();
    }

    @EventHandler
    public void on(AccountDeletedEvent accountDeletedEvent) {
        accountRepository.deleteById(accountDeletedEvent.getAccountId());
        log.info("Account Deleted {}", accountDeletedEvent);
        printThreadId();
    }

    @QueryHandler
    public AccountEntity findByAccountId(FindByAccountIdQuery findByAccountIdQuery) {
        return accountRepository.findById(findByAccountIdQuery.getAccountId()).orElseThrow();
    }

    @QueryHandler
    public List<AccountEntity> findAll(FindAllAccountsQuery findAllAccountsQuery) {
        return accountRepository.findAll();
    }

    @ResetHandler
    public void deleteRepository() {
        this.accountRepository.deleteAll();
        log.info("Deleted Account Group Repository");
    }

    private static void printThreadId() {
        log.info("Thread Id : {}", Thread.currentThread().getId());
    }

}

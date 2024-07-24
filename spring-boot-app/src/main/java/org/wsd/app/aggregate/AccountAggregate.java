package org.wsd.app.aggregate;

import lombok.*;
import org.eventa.core.aggregate.AggregateRoot;
import org.eventa.core.streotype.Aggregate;
import org.eventa.core.streotype.CommandHandler;
import org.eventa.core.streotype.EventSourcingHandler;
import org.eventa.core.streotype.RoutingKey;
import org.wsd.app.commands.CreateAccountCommand;
import org.wsd.app.commands.DeleteAccountCommand;
import org.wsd.app.commands.UpdateAccountCommand;
import org.wsd.app.events.AccountCreatedEvent;
import org.wsd.app.events.AccountDeletedEvent;
import org.wsd.app.events.AccountUpdatedEvent;

import java.util.UUID;

@Getter
@Setter
@ToString
@Aggregate
@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    @RoutingKey
    private UUID id;
    private String name;
    private String email;
    private double balance;
    private String accountType;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {

        if (createAccountCommand.getAccountName() == null || createAccountCommand.getAccountName().isEmpty()) {
            throw new RuntimeException("Account name can not be null or empty.");
        }

        apply(
                AccountCreatedEvent.builder()
                        .accountId(createAccountCommand.getAccountId())
                        .email(createAccountCommand.getEmail())
                        .accountName(createAccountCommand.getAccountName())
                        .accountType(createAccountCommand.getAccountType())
                        .balance(createAccountCommand.getBalance())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        this.id = accountCreatedEvent.getAccountId();
        this.name = accountCreatedEvent.getAccountName();
        this.email = accountCreatedEvent.getEmail();
        this.balance = accountCreatedEvent.getBalance();
        this.accountType = accountCreatedEvent.getAccountType();
    }

    @CommandHandler
    public void handle(UpdateAccountCommand updateAccountCommand) {

        if (updateAccountCommand.getAccountName() == null || updateAccountCommand.getAccountName().isEmpty()) {
            throw new RuntimeException("Account name can not be null or empty.");
        }

        apply(
                AccountUpdatedEvent.builder()
                        .accountId(updateAccountCommand.getAccountId())
                        .accountName(updateAccountCommand.getAccountName())
                        .email(updateAccountCommand.getEmail())
                        .balance(updateAccountCommand.getBalance())
                        .accountType(updateAccountCommand.getAccountType())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountUpdatedEvent accountUpdatedEvent) {
        this.id = accountUpdatedEvent.getAccountId();
        this.name = accountUpdatedEvent.getAccountName();
        this.email = accountUpdatedEvent.getEmail();
        this.balance = accountUpdatedEvent.getBalance();
        this.accountType = accountUpdatedEvent.getAccountType();
    }

    @CommandHandler
    public void handle(DeleteAccountCommand deleteAccountCommand) {

        if (deleteAccountCommand.getAccountName() == null || deleteAccountCommand.getAccountName().isEmpty()) {
            throw new RuntimeException("Account name can not be null or empty.");
        }

        apply(
                AccountDeletedEvent.builder()
                        .accountId(deleteAccountCommand.getAccountId())
                        .email(deleteAccountCommand.getEmail())
                        .accountName(deleteAccountCommand.getAccountName())
                        .accountType(deleteAccountCommand.getAccountType())
                        .balance(deleteAccountCommand.getBalance())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(AccountDeletedEvent accountDeletedEvent) {
        this.id = accountDeletedEvent.getAccountId();
        this.name = accountDeletedEvent.getAccountName();
        this.email = accountDeletedEvent.getEmail();
        this.balance = accountDeletedEvent.getBalance();
        this.accountType = accountDeletedEvent.getAccountType();
    }

}

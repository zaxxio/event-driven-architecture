package org.wsd.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eventa.core.dispatcher.CommandDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wsd.app.commands.CreateAccountCommand;
import org.wsd.app.commands.DeleteAccountCommand;
import org.wsd.app.commands.UpdateAccountCommand;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountCommand createAccountCommand) throws Exception {
        this.commandDispatcher.send(createAccountCommand, ((commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error("Error");
            } else {
                log.info(commandResultMessage.getResult());
            }
        }));
        return ResponseEntity.ok(createAccountCommand);
    }

    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestBody UpdateAccountCommand updateAccountCommand) throws Exception {
        this.commandDispatcher.send(updateAccountCommand, ((commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error(commandResultMessage.getException().getMessage());
            } else {
                log.info(commandResultMessage.getResult());
            }
        }));
        return ResponseEntity.ok(updateAccountCommand);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteAccountCommand deleteAccountCommand) throws Exception {
        this.commandDispatcher.send(deleteAccountCommand, ((commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error(commandResultMessage.getException().getMessage());
            } else {
                log.info(commandResultMessage.getResult());
            }
        }));
        return ResponseEntity.ok(deleteAccountCommand);
    }

}

package org.wsd.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eventa.core.dispatcher.CommandDispatcher;
import org.eventa.core.handler.EventProcessingHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wsd.app.commands.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountCommandController {

    private final CommandDispatcher commandDispatcher;
    private final EventProcessingHandler eventProcessingHandler;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody @Valid CreateAccountCommand createAccountCommand) throws Exception {
        this.commandDispatcher.dispatch(createAccountCommand, ((commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error(commandResultMessage.getException().getMessage());
            } else {
                log.info(commandResultMessage.getResult());
            }
        }));
        return ResponseEntity.ok(createAccountCommand);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestBody @Valid UpdateAccountCommand updateAccountCommand) throws Exception {
        this.commandDispatcher.dispatch(updateAccountCommand, ((commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error(commandResultMessage.getException().getMessage());
            } else {
                log.info(commandResultMessage.getResult());
            }
        }));
        return ResponseEntity.ok(updateAccountCommand);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestBody @Valid DeleteAccountCommand deleteAccountCommand) throws Exception {
        this.commandDispatcher.dispatch(deleteAccountCommand, ((commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error(commandResultMessage.getException().getMessage());
            } else {
                log.info(commandResultMessage.getResult());
            }
        }));
        return ResponseEntity.ok(deleteAccountCommand);
    }

    @PostMapping("/restore")
    public ResponseEntity<?> restoreDB() throws Exception {
        this.eventProcessingHandler.eventProcessor("account-group").reset();
        return ResponseEntity.ok("Restored Database.");
    }

}

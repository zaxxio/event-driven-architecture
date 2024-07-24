package org.wsd.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eventa.core.dispatcher.QueryDispatcher;
import org.eventa.core.dispatcher.ResponseType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wsd.app.entity.AccountEntity;
import org.wsd.app.query.FindAllAccountsQuery;
import org.wsd.app.query.FindByAccountIdQuery;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountQueryController {

    private final QueryDispatcher queryDispatcher;

    @GetMapping("/findByAccountId")
    public ResponseEntity<?> findByAccountId(@RequestParam @Valid UUID accountId) throws Exception {
        final FindByAccountIdQuery findByProductIdQuery = FindByAccountIdQuery.builder()
                .accountId(accountId)
                .build();
        final AccountEntity result = this.queryDispatcher.dispatch(findByProductIdQuery, ResponseType.instanceOf(AccountEntity.class));
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/findAllAccounts")
    public ResponseEntity<?> findAll() throws Exception {
        FindAllAccountsQuery findAllAccountsQuery = FindAllAccountsQuery.builder().build();
        final List<AccountEntity> result = this.queryDispatcher.dispatch(findAllAccountsQuery, ResponseType.multipleInstancesOf(AccountEntity.class));
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}

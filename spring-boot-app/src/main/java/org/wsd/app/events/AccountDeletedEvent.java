package org.wsd.app.events;

import lombok.*;
import org.eventa.core.events.BaseEvent;
import org.eventa.core.streotype.RoutingKey;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountDeletedEvent extends BaseEvent {
    @RoutingKey
    private UUID accountId;
    private String email;
    private String accountName;
    private Double balance;
    private String accountType;
}

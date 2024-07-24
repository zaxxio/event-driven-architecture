package org.wsd.app.commands;

import lombok.*;
import org.eventa.core.commands.BaseCommand;
import org.eventa.core.streotype.RoutingKey;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateAccountCommand extends BaseCommand {
    @RoutingKey
    private UUID accountId;
    private String email;
    private String accountName;
    private Double balance;
    private String accountType;
}

package org.wsd.app.query;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FindByAccountIdQuery {
    private UUID accountId;
}

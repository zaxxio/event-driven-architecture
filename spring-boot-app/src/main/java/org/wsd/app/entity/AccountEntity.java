package org.wsd.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@NoArgsConstructor
public class AccountEntity {
    @Id
    private UUID id;
    private String accountName;
    private String email;
    private String accountType;
    private Double balance;
}

package org.evgeny.hairok.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Table(name = "clients")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client extends BaseEntity {

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "Modified_at")
    @LastModifiedDate
    private LocalDate modifiedAt;

    String name;
    String surname;
    String patronymic;
    LocalDate birthday;
    String phone;
    String city;
    String password;
}

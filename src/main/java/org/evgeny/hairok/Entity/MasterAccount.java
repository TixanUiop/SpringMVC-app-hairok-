package org.evgeny.hairok.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper=false)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "master_account")
public class MasterAccount extends BaseEntity {

    String avatar;
    String password;

    @OneToOne
    @JoinColumn(name = "id_masters_info")
    MasterProfiles masterProfiles;

    String email;

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
}
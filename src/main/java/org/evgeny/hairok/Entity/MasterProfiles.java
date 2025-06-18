package org.evgeny.hairok.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;


@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "master_profiles")
public class MasterProfiles extends BaseEntity{

    String name;
    String surname;
    String patronymic;
    LocalDate birthday;
    String phone;
    String city;

    @OneToMany
    List<MasterRating> rate;

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

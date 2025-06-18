package org.evgeny.hairok.Repository;

import org.evgeny.hairok.Entity.Client;
import org.evgeny.hairok.Entity.MasterAccount;
import org.evgeny.hairok.Entity.MasterProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RegisterUserRepository extends JpaRepository<Client,Long> {


    @Modifying
    @Query("UPDATE MasterAccount set avatar = :avatar WHERE id = :id")
    Integer updateById(Long id, String avatar);

    boolean existsByPhone(String phone);

    @Query("SELECT mp.phone FROM MasterProfiles mp WHERE mp.phone = :phone")
    Optional<String> existsByMastersPhone(@Param("phone") String phone);


    @Query("SELECT ma.email FROM MasterAccount ma WHERE ma.email = :email")
    Optional<String> existsByEmail(@Param("email") String email);


    Optional<MasterAccount> save(MasterAccount masterAccount);

    Optional<MasterProfiles> save(MasterProfiles masterProfiles);


}

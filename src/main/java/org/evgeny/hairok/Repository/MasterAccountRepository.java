package org.evgeny.hairok.Repository;

import org.evgeny.hairok.Entity.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MasterAccountRepository extends JpaRepository<MasterAccount, Long> {

    Optional<MasterAccount> findByEmail(String email);

}

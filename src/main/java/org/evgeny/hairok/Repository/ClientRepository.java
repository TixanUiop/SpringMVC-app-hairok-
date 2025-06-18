package org.evgeny.hairok.Repository;

import org.evgeny.hairok.Entity.Client;
import org.evgeny.hairok.Entity.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPhone(String phone);

}

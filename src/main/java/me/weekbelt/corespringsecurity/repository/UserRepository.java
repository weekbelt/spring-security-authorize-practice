package me.weekbelt.corespringsecurity.repository;

import java.util.Optional;
import me.weekbelt.corespringsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
}

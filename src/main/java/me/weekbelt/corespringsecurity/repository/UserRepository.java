package me.weekbelt.corespringsecurity.repository;

import java.util.Optional;
import me.weekbelt.corespringsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    int countByUsername(String username);
}

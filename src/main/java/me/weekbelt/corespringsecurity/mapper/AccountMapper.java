package me.weekbelt.corespringsecurity.mapper;

import me.weekbelt.corespringsecurity.domain.Account;
import me.weekbelt.corespringsecurity.domain.AccountDto;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountMapper {

    public static Account toAccount(AccountDto accountDto, PasswordEncoder passwordEncoder) {
        return Account.builder()
            .username(accountDto.getUsername())
            .password(passwordEncoder.encode(accountDto.getPassword()))
            .email(accountDto.getEmail())
            .age(accountDto.getAge())
            .role(accountDto.getRole())
            .build();
    }
}

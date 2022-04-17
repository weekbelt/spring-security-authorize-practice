package me.weekbelt.corespringsecurity.service;


import java.util.List;
import me.weekbelt.corespringsecurity.domain.dto.AccountDto;
import me.weekbelt.corespringsecurity.domain.entity.Account;

public interface UserService {

    void createUser(Account account);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long idx);
}

package me.weekbelt.corespringsecurity.service.impl;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.weekbelt.corespringsecurity.domain.Account;
import me.weekbelt.corespringsecurity.repository.UserRepository;
import me.weekbelt.corespringsecurity.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}

package me.weekbelt.corespringsecurity.security.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.weekbelt.corespringsecurity.domain.Account;
import me.weekbelt.corespringsecurity.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("username에 해당하는 account를 찾을 수 없습니다. username: " + username));
        List<GrantedAuthority> roles = List.of(account::getRole);

        return new AccountContext(account, roles);
    }
}

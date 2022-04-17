package me.weekbelt.corespringsecurity.controller.user;


import java.security.Principal;
import me.weekbelt.corespringsecurity.domain.dto.AccountDto;
import me.weekbelt.corespringsecurity.domain.entity.Account;
import me.weekbelt.corespringsecurity.repository.RoleRepository;
import me.weekbelt.corespringsecurity.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/users")
    public String createUser() throws Exception {

        return "user/login/register";
    }

    @PostMapping(value = "/users")
    public String createUser(AccountDto accountDto) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        userService.createUser(account);

        return "redirect:/";
    }

    @GetMapping(value = "/mypage")
    public String myPage(@AuthenticationPrincipal Account account, Authentication authentication, Principal principal) throws Exception {

        return "user/mypage";
    }
}

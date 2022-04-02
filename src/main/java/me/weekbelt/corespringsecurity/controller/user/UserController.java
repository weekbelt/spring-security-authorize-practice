package me.weekbelt.corespringsecurity.controller.user;


import lombok.RequiredArgsConstructor;
import me.weekbelt.corespringsecurity.domain.Account;
import me.weekbelt.corespringsecurity.domain.AccountDto;
import me.weekbelt.corespringsecurity.mapper.AccountMapper;
import me.weekbelt.corespringsecurity.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final PasswordEncoder passwordEncoder;
	
	@GetMapping(value="/mypage")
	public String myPage() throws Exception {

		return "user/mypage";
	}

	@GetMapping("/users")
	public String createUser() {
		return "user/login/register";
	}

	@PostMapping("/users")
	public String createUser(AccountDto accountDto) {
		Account account = AccountMapper.toAccount(accountDto, passwordEncoder);
		userService.createUser(account);

		return "redirect:/";
	}
}

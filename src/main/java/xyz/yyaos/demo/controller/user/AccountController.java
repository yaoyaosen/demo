package xyz.yyaos.demo.controller.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 账户管理
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger logger = LogManager.getLogger(AccountController.class);

	/**
	 * 用户登录
	 *
	 * @param info 登录账号信息
	 * @return 用户信息
	 */
	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> info) {
		
		return "login_test";
	}
}

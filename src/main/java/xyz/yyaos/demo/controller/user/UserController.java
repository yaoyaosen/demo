package xyz.yyaos.demo.controller.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	/**
	 * 获取用户信息
	 *
	 * @param uid 用户id
	 * @return 用户信息
	 */
	@GetMapping("/info")
	public String info(@RequestParam(name = "uid", defaultValue = "wxz") String uid) {
		logger.info(uid);
		return "user_info_test";
	}
}

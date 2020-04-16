package xyz.yyaos.demo.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * 登录授权记录
 */
@Data
public class Auth {

	/**
	 * id
	 */
	private String id;

	/**
	 * 账户id
	 */
	private String accountId;

	/**
	 * token
	 */
	private String token;

	/**
	 * 刷新token
	 */
	private String refreshToken;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 通过id获取auth
	 *
	 * @param id id
	 * @return auth
	 */
	public static Auth getById(String id) {

		Auth auth = new Auth();
		auth.id = id;
		return auth;
	}

	/**
	 * 通过id获取auth
	 *
	 * @param token token
	 * @return auth
	 */
	public static Auth getByToken(String token) {

		Auth auth = new Auth();
		auth.id = "uid-001";
		auth.token = token;
		auth.refreshToken = "";
		return auth;
	}
}

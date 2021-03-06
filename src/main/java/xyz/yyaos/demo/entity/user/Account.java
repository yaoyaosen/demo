package xyz.yyaos.demo.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * 账户
 */
@Data
public class Account {

	/**
	 * id
	 */
	private String id;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;
}

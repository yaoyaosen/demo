package xyz.yyaos.demo.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
public class User {

	/**
	 * id
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 头像
	 */
	private String img;

	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 简介
	 */
	private String summary;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;
}

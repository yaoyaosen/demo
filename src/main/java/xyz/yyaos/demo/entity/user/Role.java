package xyz.yyaos.demo.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * 角色
 */
@Data
public class Role {

	/**
	 * id
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;
}

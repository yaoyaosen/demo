package xyz.yyaos.demo.auth;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationTest {

	private static final String AUTH_KEY = "yyaos@2020";
	private static final String ACCESS = "access";
	private static final String REFRESH = "refresh";
	private static final long TOKEN_EXPIRE = 7200000L;
	private static final long REFRESH_TOKEN_EXPIRE = 86400 * 30 * 1000L;

	/**
	 * algorithm
	 *
	 * @return algorithm
	 */
	private Algorithm algorithm() {
		return Algorithm.HMAC256(AUTH_KEY);
	}

	/**
	 * 生成 token
	 * 2 小时有效
	 */
	@Test
	public void createTokenTest() {

		String uid = "uid-001";
		String token = JWT.create()
				.withSubject(ACCESS)
				.withClaim("uid", uid)
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRE))
				.sign(algorithm());
		System.out.println(token);
	}

	/**
	 * 生成 Refresh Token
	 * 30 天有效期
	 *
	 * @return refreshToken
	 */
	@Test
	public void createRefreshTokenTest() {
		String uid = "uid-001";
		String refreshToken = JWT.create()
				.withSubject(REFRESH)
				.withClaim("uid", uid)
				.withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE))
				.sign(algorithm());

		System.out.println(refreshToken);
	}

	/**
	 * 获取 token 中的数据
	 */
	@Test
	public void getUidTest() {

		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhY2Nlc3MiLCJ1aWQiOiJ1aWQtMDAxIiwiZXhwIjoxNTg3MDM2NDEzfQ.duPqpoBi9QcjzCWn3FMwRSSl1nKZklGzarOOJyJVc-k";
		String json = new String(Base64.decodeBase64(token.split("\\.")[1]));
		Type type = new TypeToken<HashMap<String, String>>() {
		}.getType();
		Map<String, String> data = JSON.parseObject(json, type);

		System.out.println(data.get("uid"));
	}

	@Resource(name = "auth")
	Authorization authorization1;

	/*@Resource
	Authorization authorization2;*/

	@Test
	public void test() {
		authorization1.a = 1;
		//System.out.println(authorization2.a );
	}
}
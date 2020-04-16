package xyz.yyaos.demo.auth;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.yyaos.demo.entity.user.Auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.net.ProtocolException;
import java.util.Date;
import java.util.Optional;

@Component
public class Authorization implements HandlerInterceptor {

	private static final String AUTH_KEY = "yyaos@2020";
	private static final String AUTHORIZATION = "Authorization";
	private static final String AUTHORIZATION_PREFIX = "Bearer ";
	private static final String ACCESS = "access";
	private static final String REFRESH = "refresh";
	private static final long TOKEN_EXPIRE = 7200000L;
	private static final long REFRESH_TOKEN_EXPIRE = 86400 * 30 * 1000L;

	public Integer a;

	/**
	 *
	 */
	@Bean
	public Authorization getBean() {
		return new Authorization();
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object object) throws Exception {

		// 从 http 请求头中取出 token
		String token = getToken(req);
		JWTVerifier jwtVerifier = JWT.require(algorithm()).build();

		try {

			// 如果不是映射到方法直接通过
			if (!(object instanceof HandlerMethod)) {
				return true;
			}

			// 执行认证
			if (token == null) {
				throw new ProtocolException();
			}

			// 验证 token
			jwtVerifier.verify(token);

			return true;

		} catch (ProtocolException | JWTVerificationException | NullPointerException e) {

			try {
				// 通过token获取用户账号
				Auth auth = Auth.getByToken(token);
				if (auth == null || StringUtils.isEmpty(auth.getRefreshToken())) {
					throw new ProtocolException();
				}
				jwtVerifier.verify(auth.getRefreshToken());

				String newToken = this.createToken(auth.getAccountId());
				auth.setToken(newToken);

				// 更新token
				res.setHeader("Token", newToken);
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object object, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object o, Exception e) throws Exception {
	}

	/**
	 * 获取token中的用户id
	 */
	public String getUid(HttpServletRequest req) {

		Optional<Claims> claims = getClaims(req);
		return claims.map(Claims::getUid).orElse(null);
	}

	/**
	 * 获取 token 中的数据
	 */
	public Optional<Claims> getClaims(HttpServletRequest req) {
		try {

			String token = getToken(req);
			String json = new String(Base64.decodeBase64(token.split("\\.")[1]));

			Type type = new TypeToken<Claims>() {
			}.getType();

			Claims data = JSON.parseObject(json, type);

			return Optional.of(data);
		} catch (ProtocolException e) {

			return Optional.empty();
		}
	}

	/**
	 * 从 http请求中获取token
	 *
	 * @param req req
	 * @return token
	 * @throws ProtocolException ProtocolException
	 */
	private String getToken(HttpServletRequest req) throws ProtocolException {

		final String authorization = req.getHeader(AUTHORIZATION);
		if (StringUtils.isEmpty(authorization)) {
			throw new ProtocolException();
		}
		if (!authorization.contains(AUTHORIZATION_PREFIX)) {
			throw new ProtocolException();
		}
		//first index will contain an empty string
		return authorization.split(AUTHORIZATION_PREFIX)[1];
	}

	/**
	 * 生成 token
	 * 2 小时有效
	 *
	 * @param uid uid
	 * @return token
	 */
	private String createToken(String uid) {
		return JWT.create()
				.withSubject(ACCESS)
				.withClaim("uid", uid)
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRE))
				.sign(algorithm());
	}

	/**
	 * 生成 Refresh Token
	 * 30 天有效期
	 *
	 * @return refreshToken
	 */
	private String createRefreshToken(String uid) {
		return JWT.create()
				.withSubject(REFRESH)
				.withClaim("uid", uid)
				.withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE))
				.sign(algorithm());
	}

	/**
	 * algorithm
	 *
	 * @return algorithm
	 */
	private Algorithm algorithm() {
		return Algorithm.HMAC256(AUTH_KEY);
	}

	/**
	 * Claims
	 */
	@Data
	public static class Claims {
		private String uid;
		private String sub;
		private Long exp;
	}
}

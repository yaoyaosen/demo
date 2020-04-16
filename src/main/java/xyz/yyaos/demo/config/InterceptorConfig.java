package xyz.yyaos.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.yyaos.demo.auth.Authorization;

import java.util.Arrays;
import java.util.List;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	/**
	 * 需要进行登录权限验证的api
	 */
	private static final List<String> authApiList = Arrays.asList(
			"/user/info"
	);

	/**
	 * @param registry registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor())
				.addPathPatterns(authApiList);
	}

	/**
	 * 访问权限
	 *
	 * @return authInterceptor
	 */
	@Bean
	public Authorization authInterceptor() {
		return new Authorization();
	}
}

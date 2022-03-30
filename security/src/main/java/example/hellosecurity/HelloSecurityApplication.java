package example.hellosecurity;

import example.hellosecurity.filter.JwtAuthenticationTokenFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

/**
 *  1. 定义一个JWT的过滤器，放在SpringSecurity的框架里默认帐号密码验证的前面
 *  2. 定义一个密钥，用于创建Token给用户，在用户访问资源的时，使用Filter验证
 *
 * @author chiang
 */
@SpringBootApplication
public class HelloSecurityApplication {

	/**
	 * 根据SpringBoot官方让重复执行的filter实现一次执行过程的解决方案，参见官网
	 * 地址：https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-disable-registration-of-a-servlet-or-filter
	 * 在SpringBoot启动类中，加入以下代码：
	 */
	@Bean
	public FilterRegistrationBean registration(JwtAuthenticationTokenFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}


	public static void main(String[] args) {
		SpringApplication.run(HelloSecurityApplication.class, args);
//		generateSecretKey();
	}

	/**
	 * 生成密钥：https://github.com/jwtk/jjwt#install-jdk-maven 的 Creating Safe Keys
	 */
	public static String generateSecretKey(){
		// Key的类型要和签名使用的类型一致
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String secretKey = Encoders.BASE64.encode(key.getEncoded());
		System.out.println(secretKey);
		System.out.println(secretKey.length());
		return secretKey;
	}

}

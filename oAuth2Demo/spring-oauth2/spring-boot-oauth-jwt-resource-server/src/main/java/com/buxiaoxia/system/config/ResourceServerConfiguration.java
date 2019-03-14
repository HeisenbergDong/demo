package com.buxiaoxia.system.config;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 自定义资源服务器配置
 * <p>
 * Created by xw on 2017/3/19.
 * 2017-03-19 8:09
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


	@Override
	public void configure(final HttpSecurity http) throws Exception {
		/*ALWAYS
			总是创建HttpSession
		IF_REQUIRED
			Spring Security只会在需要时创建一个HttpSession
		NEVER
			Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
		STATELESS
			Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext*/
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .antMatcher("/**")
                .authorizeRequests()
                .anyRequest().authenticated();
//                .authorizeRequests()
//				.anyRequest().authenticated();

	}

	@Override
	public void configure(ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		Resource resource = new ClassPathResource("public.txt");
		String publicKey = null;
		try {
			publicKey = inputStream2String(resource.getInputStream());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		converter.setVerifierKey(publicKey);
		converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

	String inputStream2String(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}

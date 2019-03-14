package com.example.oauthresource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/**").authenticated().antMatchers(HttpMethod.GET, "/user")
//                // 拦截用户，必须具有所列权限
//                .hasAuthority("USER_WRITE");
        // .antMatchers(HttpMethod.POST, "/foo").hasAuthority("FOO_WRITE");
        http.csrf().disable();
        http.authorizeRequests()
//                .antMatchers("/user").authenticated()
//                .antMatchers("/user").hasRole("USER_WRITE")
                .anyRequest().authenticated();
//                .and().exceptionHandling()
//                // 没授权的用户将会跳转到此页面
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("http://localhost:8080/oauth/authorize?client_id=client&response_type=code&redirect_uri=http://www.baidu.com"));
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(defaultTokenServices());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //与授权服务器使用共同的密钥进行解析
        converter.setSigningKey("jwtTest");
//        Resource resource = new ClassPathResource("public.txt");
//        String publicKey = null;
//        try {
//            publicKey = IOUtils.toString(resource.getInputStream());
//        } catch (final IOException e) {
//            throw new RuntimeException(e);
//        }
//        converter.setVerifierKey(publicKey);

        return converter;
    }

    @Bean
    public ResourceServerTokenServices defaultTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}

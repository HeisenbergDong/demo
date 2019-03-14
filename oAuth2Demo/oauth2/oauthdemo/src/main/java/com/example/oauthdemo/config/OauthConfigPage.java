package com.example.oauthdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class OauthConfigPage extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 初始化客户端信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in-memory存储
                .withClient("client") // client_id
                .secret("secret") // client_secret
                .authorizedGrantTypes("authorization_code","refresh_token") // 该client允许的授权类型
                .scopes("app");
    }

    /**
     * 定义授权和令牌端点以及令牌服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //自定数据加入jwt令牌
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints.tokenStore(tokenStore())
//                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);
    }

    /**
     * TokenEnhancer : 令牌增强器
     * 使用TokenEhancer中的 ehance方法。
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    /**
     * token令牌持久化
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


    /**
     * 根据签名生成JwtToken，需要在认证服务器与资源服务器类里注入。
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("jwtTest");
//        KeyStoreKeyFactory keyStoreKeyFactory =  new KeyStoreKeyFactory(new ClassPathResource("/mytest.jks"), "mypass".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
        return converter;

    }

    public static void main(String args[]){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImp0aSI6Ijc3NDY3MzM2LTA5MTUtNDkxZC05ZGIwLTJmNmUyYjk3MWYwZSIsImlhdCI6MTU1MTY2NTMzNiwiZXhwIjoxNTUxNjY4OTM2fQ.lVTZTBvkf1v1eRuu6oJlV16pKsuw0slbpkxYgtWo05w";
        Jwt jwt = JwtHelper.decode(token);
        System.out.println(jwt.toString());
    }
}

package com.tuoheng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //配置客户端的详细信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//使用内存存储
                .withClient("c1")//client_id,用于客户端的标记,必须有
                .secret(new BCryptPasswordEncoder().encode("secret"))//采用Bcrpt方式加密,明文是secret
                .resourceIds("res1")
                .authorizedGrantTypes("authorization_code", "password", "client_credientials", "implicit", "refresh_token")//该client允许授权的类型
                .scopes("all")//允许的授权的范围
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");//验证的回调地址
    }


    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;

    //定义令牌服务类AuthrizatiohServerTokenService的bean方法
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices dts = new DefaultTokenServices();//令牌服务对象
        dts.setClientDetailsService(clientDetailsService);//封装客户端详细信息
        dts.setSupportRefreshToken(true);//支持刷新令牌有效期
        dts.setTokenStore(tokenStore);//令牌存储策略
        dts.setAccessTokenValiditySeconds(7200);//默认有效期2小时
        dts.setRefreshTokenValiditySeconds(259200);//设置刷新令牌有效时长3天
        return dts;
    }


    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    //令牌访问端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)//封装认证管理器
                .authorizationCodeServices(authorizationCodeServices)//封装认证代码服务
                .tokenServices(tokenService())//添加令牌服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    //设置授权码模式的授权码如何存取,暂时采用内存模式
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    //配置令牌端点的安全约束,暴露端点/oauth/token_key和端点/oauth/check_token
    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) {
        securityConfigurer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
}

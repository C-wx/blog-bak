package cbuc.life.gateway.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: cbuc
 * @data: 2021-04-19 23:11
 * @description:
 */
@Configuration
public class ShiroConfig {

    /**
     * 如果没有权限,shiro框架就会自动请求这个地址
     */
    @Value("${authorization.shiro.unauthorizedUrl:/login.html}")
    private String unauthorizedUrl;

    /**
     * 认证成功自动跳转
     */
    @Value("${authorization.shiro.successUrl:/index.html}")
    private String successUrl;


    /**
     * 解决无法使用@Value注入问题
     *
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customAuthorizingRealm());
        securityManager.setCacheManager(shiroEhcacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * rememberMeManager管理器，写cookie，取出cookie生成用户信息
     *
     * @return RememberMeManager
     */
    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }

    /**
     * 记住我cookie
     *
     * @return Cookie
     */
    @Bean
    public Cookie rememberMeCookie() {
        SimpleCookie rememberMeCookie = new SimpleCookie();
        //rememberMe是cookie的名字
        rememberMeCookie.setName("rememberMe");
        //记住我cookie生效时间30天
        rememberMeCookie.setMaxAge(2592000);
        return rememberMeCookie;
    }

    /**
     * 自定义Realm
     *
     * @return CustomRealm
     */
    @Bean
    public CustomRealm customAuthorizingRealm() {
        return new CustomRealm();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    /**
     * 用户授权信息Cache, 采用EhCache
     *
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager shiroEhcacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * 指定本系统sessionId
     * 默认为: JSESSIONID
     * 问题: 与SERVLET容器名冲突, 如JETTY, TOMCAT 等默认JSESSIONID
     * 当跳出SHIRO SERVLET时如ERROR-PAGE容器会为JSESSIONID重新分配值导致登录会话丢失!
     *
     * @return SimpleCookie
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("SCMP_SESSION_ID");
        return simpleCookie;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroPermissionFactory = new ShiroFilterFactoryBean();
        shiroPermissionFactory.setSecurityManager(securityManager);
        //如果认证没有通过,shiro框架就会自动请求这个地址
        shiroPermissionFactory.setLoginUrl("/index.html");
        //如果没有权限,shiro框架就会自动请求这个地址
        shiroPermissionFactory.setUnauthorizedUrl(unauthorizedUrl);
        shiroPermissionFactory.setSuccessUrl(successUrl);

        Map<String, Filter> filters = new HashMap<>(16);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setUsernameParam("username");
        //表单中密码的input名称
        authenticationFilter.setPasswordParam("password");
        //记住我的input名称
        authenticationFilter.setRememberMeParam("rememberMe");
        filters.put("authc", authenticationFilter);
        shiroPermissionFactory.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**/**", "anon");
        //从上往下执行 优先执行的放前面
        //anon 允许匿名访问（资源文件）
        //authc允许登录验证访问（所有页面）
        //perms运行权限验证访问
        //logout自动退出（url可以不存在）
        //user 记住我或认证通过可以访问的地址 /index.html=user
        //authcBasic不关闭浏览器不清除session
        shiroPermissionFactory.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroPermissionFactory;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     *
     * @return LifecycleBeanPostProcessor
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * shiro权限注解支持开启
     *
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 凭证匹配器
     *
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //加密算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(10);
        return credentialsMatcher;
    }

    /**
     * @return MethodInvokingFactoryBean 实例
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(securityManager());
        return bean;
    }


}

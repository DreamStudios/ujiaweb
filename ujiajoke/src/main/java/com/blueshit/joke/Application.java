package com.blueshit.joke;

import com.blueshit.joke.repository.BaseRepositoryFactoryBean;
import com.blueshit.joke.repository.UserInfoRepository;
import com.blueshit.joke.security.LoginFilter;
import com.blueshit.joke.service.AdService;
import com.blueshit.joke.service.JokeService;
import com.blueshit.joke.service.TypeInfoService;
import com.blueshit.joke.service.UserInfoService;
import com.blueshit.joke.service.impl.AdServiceImpl;
import com.blueshit.joke.service.impl.JokeServiceImpl;
import com.blueshit.joke.service.impl.TypeInfoServiceImpl;
import com.blueshit.joke.service.impl.UserInfoServiceImpl;
import com.blueshit.joke.utils.AuthorizationUser;
import com.blueshit.joke.utils.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.catalina.connector.Connector;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import java.util.Properties;

/**
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 202014/11/8 11:48
 * @description
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaRepositories(
        basePackages = "com.blueshit.joke.repository",
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
public class Application extends SpringBootServletInitializer {

    /**
     * SpringBoot启动项目
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("tadirect.jasypt.key", "password");
        SpringApplication.run(Application.class, args);
    }

    //容器启动项目
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //数据库密码加密密钥：
        System.setProperty("tadirect.jasypt.key", "password");
        return application.sources(Application.class);
    }

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, characterEncodingFilter());
        insertFilters(servletContext, loginFilter());
    }

    /**
     * 登陆时，过滤验证码
     */
    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    /**
     * 字符集过滤
     * @return
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(Constants.Common.URLENC);
        characterEncodingFilter.setForceEncoding(true);
        System.out.println("字符集设置为UTF-8");
        return characterEncodingFilter;
    }

    // 修改tomcat UseBodyEncodingForURI参数为true
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.getTomcatConnectorCustomizers().add(
                new TomcatConnectorCustomizer() {

                    @Override
                    public void customize(Connector connector) {
                        connector.setURIEncoding("utf-8");
                        connector.setUseBodyEncodingForURI(true);
                        System.out
                                .println("Tomcat connector UseBodyEncodingForURI=true");
                    }

                });
        return factory;
    }

    //加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("密码：123456 加密后是：" + new BCryptPasswordEncoder(10).encode("123456"));
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("password");
        System.out.println("数据库密码:root 加密后是：ENC(" + encryptor.encrypt("root") + ")");
        System.out.println("数据库解密密码：" + encryptor.decrypt("ScP+tGzO23sar/YQ4/6ZigFFK5xMvKl9"));
        return new BCryptPasswordEncoder(10);
    }

    /**
     * 验证码插件普通servlet方式：
     * 此方式对于普通jsp环境下面是有效的，如果在spring mvc环境下，则取不到session值，对于sping mvc环境此用第二种方式
     * @return
     */
    /*@Bean
    public ServletRegistrationBean kaptchaRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new com.google.code.kaptcha.servlet.KaptchaServlet());
        registration.addInitParameter("kaptcha.border", "no");
        registration.addInitParameter("kaptcha.image.width", "110");
        registration.addInitParameter("kaptcha.image.height", "40");
        registration.addInitParameter("kaptcha.textproducer.font.color", "black");
        registration.addInitParameter("kaptcha.textproducer.font.size", "30");
        registration.addInitParameter("kaptcha.textproducer.char.space", "5");
        //registration.addInitParameter("kaptcha.background.clear.from", "green");
        //registration.addInitParameter("kaptcha.background.clear.to", "gray");
       // registration.addUrlMappings("/kaptcha.jpg");
        return registration;
    }*/

    /**
     * 验证码插件:第二种方式
     * @return
     */
    @Bean
    public DefaultKaptcha captchaProducer(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(getConfig());
        return defaultKaptcha;
    }

    @Bean
    public Config getConfig(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border","yes");
        properties.setProperty("kaptcha.border.color","105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        properties.setProperty("kaptcha.image.width", "110");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size","30");
        properties.setProperty("kaptcha.session.key","code");
        properties.setProperty("kaptcha.textproducer.char.lengt","4");
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        return config;
        /*
        kaptcha.border  是否有边框  默认为true  我们可以自己设置yes，no
        kaptcha.border.color   边框颜色   默认为Color.BLACK
        kaptcha.border.thickness  边框粗细度  默认为1
        kaptcha.producer.impl   验证码生成器  默认为DefaultKaptcha
        kaptcha.textproducer.impl   验证码文本生成器  默认为DefaultTextCreator
        kaptcha.textproducer.char.string   验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
        kaptcha.textproducer.char.length   验证码文本字符长度  默认为5
        kaptcha.textproducer.font.names    验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        kaptcha.textproducer.font.size   验证码文本字符大小  默认为40
        kaptcha.textproducer.font.color  验证码文本字符颜色  默认为Color.BLACK
        kaptcha.textproducer.char.space  验证码文本字符间距  默认为2
        kaptcha.noise.impl    验证码噪点生成对象  默认为DefaultNoise
        kaptcha.noise.color   验证码噪点颜色   默认为Color.BLACK
        kaptcha.obscurificator.impl   验证码样式引擎  默认为WaterRipple
        kaptcha.word.impl   验证码文本字符渲染   默认为DefaultWordRenderer
        kaptcha.background.impl   验证码背景生成器   默认为DefaultBackground
        kaptcha.background.clear.from   验证码背景颜色渐进   默认为Color.LIGHT_GRAY
        kaptcha.background.clear.to   验证码背景颜色渐进   默认为Color.WHITE
        kaptcha.image.width   验证码图片宽度  默认为200
        kaptcha.image.height  验证码图片高度  默认为50
        */
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("104857600KB");
        factory.setMaxRequestSize("104857600KB");
        return factory.createMultipartConfig();
    }


    /** top几的UserInfo对象接口  */
    @Bean
    public UserInfoService userInfoService() {
        return new UserInfoServiceImpl();
    }

    /** 获取session中的登陆对象(UserInfo)*/
    @Bean
    public AuthorizationUser authorizationUserService() {
        return new AuthorizationUser();
    }

    /** 获取笑话接口对象*/
    @Bean
    public JokeService jokeService() {
        return new JokeServiceImpl();
    }

    /**
     * 获取广告接口对象
     * @return
     */
    @Bean
    public AdService adService() {
        return new AdServiceImpl();
    }

    /**
     * 获取笑点分类接口对象
     * @return
     */
    @Bean
    public TypeInfoService typeInfoService() {
        return new TypeInfoServiceImpl();
    }
}

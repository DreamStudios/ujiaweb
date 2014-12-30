package com.blueshit.joke.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 发送邮件的配置
 */
@ConfigurationProperties(prefix = "adweb.mail")
public class MailProperties {
    //是否发送邮件
    private boolean isopen;
    //用户名
    private String username;
    //密码
    private String password;
    //地址_from
    private String fromaddress;

    Smtp smtp = new Smtp();

    public class Smtp{
        private String auth;
        private String host;
        private String port;

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }

    public boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress;
    }

    public Smtp getSmtp() {
        return smtp;
    }

    public void setSmtp(Smtp smtp) {
        this.smtp = smtp;
    }
}

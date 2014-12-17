package com.blueshit.joke.security;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.spring31.properties.EncryptablePropertiesPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class EncryptedPropertySourceLoader implements PropertySourceLoader, PriorityOrdered {
    private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    public EncryptedPropertySourceLoader() {
        //TODO: this could be taken from an environment variable
        this.encryptor.setPassword(System.getProperty("tadirect.jasypt.key"));
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{"properties"};
    }

    @Override
    public PropertySource<?> load(final String name, final Resource resource, final String profile) throws IOException {
        if (profile == null) {
            //load the properties
            final Properties props = PropertiesLoaderUtils.loadProperties(resource);

            if (!props.isEmpty()) {
                //create the encryptable properties property source
                return new EncryptablePropertiesPropertySource(name, props, this.encryptor);
            }
        }

        return null;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
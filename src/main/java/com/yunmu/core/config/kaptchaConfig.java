package com.yunmu.core.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by 13544 on 2019/5/19.
 */
@Configuration
public class kaptchaConfig {
        @Bean(name = "captchaProducer")
        public DefaultKaptcha getKaptchaBean() {
            DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
            Properties properties = new Properties();
            properties.setProperty("kaptcha.border", "yes");
            properties.setProperty("kaptcha.border.color", "105,179,90");
            properties.setProperty("kaptcha.textproducer.font.color", "blue");
            properties.setProperty("kaptcha.image.width", "70");
            properties.setProperty("kaptcha.image.height", "26");
            properties.setProperty("kaptcha.textproducer.font.size", "28");
            properties.setProperty("kaptcha.session.key", "kaptchaCode");
            properties.setProperty("kaptcha.textproducer.char.spac", "35");
            properties.setProperty("kaptcha.textproducer.char.length", "5");
            properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
            properties.setProperty("kaptcha.noise.color", "white");
            Config config = new Config(properties);
            defaultKaptcha.setConfig(config);
            System.out.println("该类加载了");
            return defaultKaptcha;
        }

        @Bean(name = "captchaProducerMath")
        public DefaultKaptcha getKaptchaBeanMath() {
            DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
            Properties properties = new Properties();
            properties.setProperty("kaptcha.border", "yes");
            properties.setProperty("kaptcha.border.color", "105,179,90");
            properties.setProperty("kaptcha.textproducer.font.color", "blue");
            properties.setProperty("kaptcha.image.width", "79");
            properties.setProperty("kaptcha.image.height", "26");
            properties.setProperty("kaptcha.textproducer.font.size", "26");
            properties.setProperty("kaptcha.session.key", "kaptchaCodeMath");
            properties.setProperty("kaptcha.textproducer.impl", "com.yunmu.core.util.KaptchaTextCreator");
            properties.setProperty("kaptcha.textproducer.char.spac", "5");
            properties.setProperty("kaptcha.textproducer.char.length", "6");
            properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
            properties.setProperty("kaptcha.noise.color", "white");
            properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
            properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
            Config config = new Config(properties);
            defaultKaptcha.setConfig(config);
            return defaultKaptcha;
        }

}

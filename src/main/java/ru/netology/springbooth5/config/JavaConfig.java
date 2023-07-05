package ru.netology.springbooth5.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springbooth5.profile.DevProfile;
import ru.netology.springbooth5.profile.ProductionProfile;
import ru.netology.springbooth5.profile.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "true", matchIfMissing = true)//Обратите внимание, что сейчас, если проперти не задана,
    // приложение упадёт с ошибкой, что невозможно найти бин такого типа. Часто делают бин по умолчанию с помощью параметра matchIfMissing = true
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}

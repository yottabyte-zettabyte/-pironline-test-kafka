
package com.pironline.test.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pironline.test.gson.LocalDateTypeAdapter;
import java.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public Gson gsonService() {
        return new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();
    }
}

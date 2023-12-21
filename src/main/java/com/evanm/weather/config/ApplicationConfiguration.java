package com.evanm.weather.config;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public DecimalFormat decimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("###.####");
        decimalFormat.setMinimumFractionDigits(0);
        decimalFormat.setMaximumFractionDigits(4);
        decimalFormat.setMinimumIntegerDigits(1);
        decimalFormat.setMaximumIntegerDigits(3);
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        return decimalFormat;
    }
}

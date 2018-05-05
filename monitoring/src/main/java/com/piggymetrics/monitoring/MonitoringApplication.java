package com.piggymetrics.monitoring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.cloud.stream.converter.CompositeMessageConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.converter.ConfigurableCompositeMessageConverter;

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class MonitoringApplication {

  public static void main(String[] args) {
    final SpringApplication application = new SpringApplication(MonitoringApplication.class);
    application.setBannerMode(Banner.Mode.OFF);
    application.setWebApplicationType(WebApplicationType.SERVLET);
    application.run(args);
  }

  @Bean
  public ConfigurableCompositeMessageConverter integrationArgumentResolverMessageConverter(
      CompositeMessageConverterFactory factory) {
    return new ConfigurableCompositeMessageConverter(
        factory.getMessageConverterForAllRegistered().getConverters());
  }
}

package ru.tusur.udo.sensors;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import java.util.Map;

@Configuration   //аннотация для обозначения конфигурации
@ComponentScan(basePackages = {"ru.tusur.udo.sensors"}) //сканировать все пакеты по пути, на выявление @Bean
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
        //аннотация для обозначения конфигурации
    ApplicationContext xmlContext() {
        return new ClassPathXmlApplicationContext("ApplicationConfig.xml");
    }

    @Bean
    public Map<String, FakeSensor> fakeSensorMap() {
        return xmlContext().getBeansOfType(FakeSensor.class);
    }

    @Bean
    ru.tusur.udo.sensors.SensorsRoutes sensorsRouts() {
        return new SensorsRoutes();
    }

    @Bean
    CamelContext camelContext() throws Exception {
        CamelContext ctx = new DefaultCamelContext();
        ctx.addRoutes(sensorsRouts());
        ctx.start();
        return ctx;
    }
}

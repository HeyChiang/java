package com.spring.enums.get;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * get方法注解转换器
 * @author Chiang
 */
@Configuration
public class MyEnumConfig extends WebMvcConfigurationSupport {
   @Override
   public FormattingConversionService mvcConversionService() {
       FormattingConversionService f = super.mvcConversionService();
       f.addConverter(new MyCustomEnumConverter());
       return f;
   }
}
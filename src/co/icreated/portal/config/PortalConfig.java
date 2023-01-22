package co.icreated.portal.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import co.icreated.portal.controller.CommonController;
import co.icreated.portal.controller.InvoiceController;
import co.icreated.portal.controller.PaymentController;
import co.icreated.portal.controller.UserController;
import co.icreated.portal.mapper.CommonMapperImpl;
import co.icreated.portal.mapper.InvoiceMapperImpl;
import co.icreated.portal.mapper.PaymentMapperImpl;
import co.icreated.portal.mapper.UserMapperImpl;
import co.icreated.portal.security.SessionUserDetailsService;
import co.icreated.portal.service.CommonService;
import co.icreated.portal.service.EmailService;
import co.icreated.portal.service.InvoiceService;
import co.icreated.portal.service.PaymentService;
import co.icreated.portal.service.UserService;
import co.icreated.portal.utils.PortalExceptionHandler;

@Configuration
// TODO ClassLoader Component scanning not working -> beans have to been imported manually
// @ComponentScan("co.icreated.portal")
//@formatter:off
@Import({
	SecurityConfig.class, MvcConfig.class, PortalExceptionHandler.class,
	SessionUserDetailsService.class, EmailService.class,
	CommonController.class, CommonService.class, CommonMapperImpl.class,
	InvoiceController.class, InvoiceService.class, InvoiceMapperImpl.class,
    PaymentController.class, PaymentService.class, PaymentMapperImpl.class,
    UserController.class, UserService.class, UserMapperImpl.class
    })
//@formatter:on
@PropertySource(value = {"classpath:webportal.properties"})
public class PortalConfig {


  @Autowired
  private Environment env;

  @Bean("ctx")
  Properties getCtx() {

    Properties props = new Properties();
    props.setProperty("#AD_Client_ID", env.getProperty("ctx.AD_Client_ID"));
    props.setProperty("#AD_Org_ID", env.getProperty("ctx.AD_Org_ID"));
    return props;
  }

  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }

}


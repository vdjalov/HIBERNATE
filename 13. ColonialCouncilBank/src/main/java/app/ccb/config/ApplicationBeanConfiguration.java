package app.ccb.config;

import app.ccb.util.FileUtil;
import app.ccb.util.FileUtilImpl;
import app.ccb.util.ValidationUtil;
import app.ccb.util.ValidationUtilImpl;
import app.ccb.util.XmlParser;
import app.ccb.util.XmlParserImpl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public Gson gson() {
		return new Gson();
    }
    
    @Bean
    public XmlParser xmlParser() {
    	return new XmlParserImpl();
    }
}






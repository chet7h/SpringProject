package stackjava.com.sbsecurityhibernate.properties;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data

@Component
//@EnableConfigurationProperties
@ConfigurationProperties("abc123")
@PropertySource("classpath:numberPhoneBegin.yml")
public class NumberPhoneBeginProperties {
	private Map<String, String> synonyms;
}
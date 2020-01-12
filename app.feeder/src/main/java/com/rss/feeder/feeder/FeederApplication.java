package com.rss.feeder.feeder;

import com.rss.feeder.feeder.domain.FeedItem;
import com.rss.feeder.feeder.domain.FeedItemRepository;
import com.rss.feeder.feeder.service.FeederService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

@RestController
@SpringBootApplication
public class FeederApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FeederApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FeederApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	CommandLineRunner commandLineRunner(FeedItemRepository repository) {
		return args -> {
			repository.save(new FeedItem("Mesaj FeederApplication"));
			repository.save(new FeedItem("Mesaj DispatcherServlet"));
			repository.save(new FeedItem("Mesaj ThreadPoolTaskExecutor"));
			repository.save(new FeedItem("Mesaj SchemaCreatorImpl"));
		};
	}

//	@Bean
//	CommandLineRunner commandLineRunner(FeederService feederService) {
//		return (string) -> { //sunny-mender-262709
//			feederService.makeTheRequest();
//		};
//	}

}

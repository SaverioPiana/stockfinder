package siw.stockfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@SpringBootApplication
@EnableScheduling
public class StockfinderApplication{

	public static void main(String[] args) {
		SpringApplication.run(StockfinderApplication.class, args);
	}

	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			String absolutePathToImages = Paths.get("src/main/upload").toAbsolutePath().toString();
			registry.addResourceHandler("/upload/**")
					.addResourceLocations("file:" + absolutePathToImages + "/")
					.setCachePeriod(0);
		}
	}

}

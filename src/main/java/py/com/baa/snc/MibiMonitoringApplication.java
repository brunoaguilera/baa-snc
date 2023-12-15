package py.com.baa.snc;

import java.util.concurrent.Executor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import py.com.baa.snc.config.MibiMonitoringBanner;

@SpringBootApplication
@EnableScheduling
public class MibiMonitoringApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.banner(new MibiMonitoringBanner()).sources(MibiMonitoringApplication.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder().banner(new MibiMonitoringBanner()).sources(MibiMonitoringApplication.class)
				.run(args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("MibiMonitoringAsync-");
		executor.initialize();
		return executor;
	}

}

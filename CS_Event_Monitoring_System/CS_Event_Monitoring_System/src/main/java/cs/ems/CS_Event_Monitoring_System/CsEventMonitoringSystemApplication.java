package cs.ems.CS_Event_Monitoring_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({
	@ComponentScan("cs.ems.service"),
	@ComponentScan("cs.ems.repository"),
	@ComponentScan("cs.ems.bean")
})
@EnableJpaRepositories("cs.ems.repository")
@EntityScan("cs.ems.bean")
public class CsEventMonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsEventMonitoringSystemApplication.class, args);
	}

}

package com.example.contentmanagementsystem.utility;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Configuration //For Auditing
@EnableJpaAuditing //For Auditing
public class AuditingConfig {
	@Bean
	AuditorAware<String> auditor(){
		return ()->Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}

package xohoon.devTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xohoon.devTask.repository.ResourcesRepository;
import xohoon.devTask.service.SecurityResourcesService;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourcesService securityResourcesService(ResourcesRepository resourcesRepository) {
        SecurityResourcesService resourcesService = new SecurityResourcesService(resourcesRepository);
        return resourcesService;
    }
}

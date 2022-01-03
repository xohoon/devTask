package xohoon.devTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xohoon.devTask.repository.admin.AccessIpRepository;
import xohoon.devTask.repository.admin.ResourcesRepository;
import xohoon.devTask.service.SecurityResourcesService;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourcesService securityResourcesService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        SecurityResourcesService resourcesService = new SecurityResourcesService(resourcesRepository, accessIpRepository);
        return resourcesService;
    }
}

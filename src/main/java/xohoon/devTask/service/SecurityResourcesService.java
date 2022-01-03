package xohoon.devTask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import xohoon.devTask.domain.entity.Resources;
import xohoon.devTask.repository.AccessIpRepository;
import xohoon.devTask.repository.ResourcesRepository;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SecurityResourcesService {

    private ResourcesRepository resourcesRepository;
    private AccessIpRepository accessIpRepository;

    public SecurityResourcesService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        this.resourcesRepository = resourcesRepository;
        this.accessIpRepository = accessIpRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllResources(); // db에 있는 모든 resources 받아온다

        resourcesList.forEach(re -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            re.getRoleSet().forEach(ro -> { // resource 와 매핑된 권한정보를 담아서 1:n 형식의 권한정보를 put
                configAttributeList.add(new SecurityConfig(ro.getRoleName()));
                result.put(new AntPathRequestMatcher(re.getResourceName()), configAttributeList);
            });
        });
        log.debug("cache test");
        return result;
    }

    public List<String> getAccessIpList() {
        List<String> accessIpList = accessIpRepository.findAll().stream().map(accessIp -> accessIp.getIpAddress()).collect(Collectors.toList());
        return accessIpList;
    }
}

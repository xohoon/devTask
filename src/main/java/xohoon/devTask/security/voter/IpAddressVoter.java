package xohoon.devTask.security.voter;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import xohoon.devTask.service.SecurityResourcesService;

import java.util.Collection;
import java.util.List;

public class IpAddressVoter implements AccessDecisionVoter<Object> {
    private SecurityResourcesService securityResourcesService;

    public IpAddressVoter(SecurityResourcesService securityResourcesService) {
        this.securityResourcesService = securityResourcesService;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return (attribute.getAttribute() != null);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        // 접속자 IP 정보 가져오기
        WebAuthenticationDetails details = (WebAuthenticationDetails)authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();
        // db 에서 가져오기
        List<String> accessIpList = securityResourcesService.getAccessIpList();
        // 비교
        int result = ACCESS_DENIED;

        for (String ipAddress : accessIpList) {
            if (remoteAddress.equals(ipAddress)) {
                return ACCESS_ABSTAIN;
            }
        }

        if (result == ACCESS_DENIED) {
            throw new AccessDeniedException("Invalid IpAddress");
        }
        return result;
    }
}

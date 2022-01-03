package xohoon.devTask.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.admin.AccessIp;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {
    AccessIp findByIpAddress(String IpAddress);
}

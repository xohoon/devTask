package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.AccessIp;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {
    AccessIp findByIpAddress(String IpAddress);
}

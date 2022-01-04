package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}

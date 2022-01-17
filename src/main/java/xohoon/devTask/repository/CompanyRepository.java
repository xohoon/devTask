package xohoon.devTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.task.Task;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByMember_id(Long id);

    void deleteById(Long id);
}

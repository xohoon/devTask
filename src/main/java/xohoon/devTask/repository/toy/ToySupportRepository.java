package xohoon.devTask.repository.toy;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Toy.ToySupport;

import java.util.List;

public interface ToySupportRepository extends JpaRepository<ToySupport, Long> {
    List<ToySupport> findAllByMember_id(Long id);
}

package xohoon.devTask.repository.toy;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Toy.ToySupport;

public interface ToySupportRepository extends JpaRepository<ToySupport, Long> {
}

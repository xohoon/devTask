package xohoon.devTask.repository.toy;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Toy.Toy;

public interface ToyRepository extends JpaRepository<Toy, Long> {
}

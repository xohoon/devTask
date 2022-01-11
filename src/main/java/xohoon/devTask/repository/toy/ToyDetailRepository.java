package xohoon.devTask.repository.toy;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Toy.ToyDetail;

public interface ToyDetailRepository extends JpaRepository<ToyDetail, Long> {
}

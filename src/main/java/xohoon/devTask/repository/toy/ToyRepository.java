package xohoon.devTask.repository.toy;

import org.springframework.data.jpa.repository.JpaRepository;
import xohoon.devTask.domain.entity.Toy.Toy;

import java.util.List;

public interface ToyRepository extends JpaRepository<Toy, Long> {
    List<Toy> findAllByMember_id(Long id);
}

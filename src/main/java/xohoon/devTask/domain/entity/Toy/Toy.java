package xohoon.devTask.domain.entity.Toy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Toy {
    @Id @GeneratedValue
    @Column(name = "toy_id")
    private Long id;
}

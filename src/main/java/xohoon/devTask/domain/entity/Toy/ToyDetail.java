package xohoon.devTask.domain.entity.Toy;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ToyDetail {
    @Id
    @GeneratedValue
    @Column(name = "td_id")
    private Long id;

    // toyDetail N : 1 toy
    @ManyToOne
    @JoinColumn(name = "toy_id")
    private Toy toy;

    // toyDetail 1 : N toySupport
    @OneToMany(mappedBy = "toyDetail")
    private List<ToySupport> toySupports = new ArrayList<>();
}

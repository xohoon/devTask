package xohoon.devTask.domain.entity.Toy;

import lombok.Data;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;

@Entity
@Data
public class ToySupport {
    @Id
    @GeneratedValue
    @Column(name = "ts_id")
    private Long id;

    // toyDetail N : M member
    // toySupport N : 1 toyDetail
    @ManyToOne
    @JoinColumn(name = "td_id")
    private ToyDetail toyDetails;

    // toySupport N : 1 member
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member members;
}

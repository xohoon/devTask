package xohoon.devTask.domain.entity.Toy;

import lombok.Data;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;

@Entity
@Data
public class ToySupport {
    @Id
    @GeneratedValue
    @Column(name = "toy_support_id")
    private Long id;

    // toyDetail N : M member
    // toySupport N : 1 toyDetail
    @ManyToOne
    @JoinColumn(name = "task_detail_id")
    private ToyDetail toyDetail = new ToyDetail();

    // toySupport N : 1 member
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member = new Member();
}
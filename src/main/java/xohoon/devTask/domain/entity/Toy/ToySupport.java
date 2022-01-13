package xohoon.devTask.domain.entity.Toy;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;

@Entity
@Data
public class ToySupport {
    @Id
    @GeneratedValue
    @Column(name = "toy_support_id")
    private Long id;
    @ColumnDefault("0")
    private int support_status; // 요청 상태
    @ColumnDefault("0")
    private int success_status; // 완료 상태

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

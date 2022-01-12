package xohoon.devTask.domain.entity.Toy;

import lombok.Data;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Toy {
    @Id @GeneratedValue
    @Column(name = "toy_id")
    private Long id;
    private String toy_title;
    private String toy_content;
    private String toying_day;
    private int toy_status;

    // toy 1 : 1 member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // toy 1 : N toyDetail
    @OneToMany(mappedBy = "toy")
    private List<ToyDetail> toyDetails = new ArrayList<>();
}

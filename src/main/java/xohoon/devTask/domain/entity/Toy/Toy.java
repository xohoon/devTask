package xohoon.devTask.domain.entity.Toy;

import lombok.*;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"id", "toy_title", "toy_content"})
public class Toy {
    @Id @GeneratedValue
    @Column(name = "toy_id")
    private Long id;
    private String toy_title;
    private String toy_content;
    private String toy_dead_day;
    private int toy_status;

    // toy N : 1 member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // toy 1 : N toyDetail
    @OneToMany(mappedBy = "toy")
    private List<ToyDetail> toyDetails = new ArrayList<>();
}

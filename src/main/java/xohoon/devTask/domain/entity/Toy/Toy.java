package xohoon.devTask.domain.entity.Toy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    // toy 1 : 1 member
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // toy 1 : N toyDetail
    @OneToMany(mappedBy = "toy")
    private List<ToyDetail> toyDetails = new ArrayList<>();
}

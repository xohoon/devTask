package xohoon.devTask.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dev {
    @Id @GeneratedValue
    @Column(name = "dev_id")
    private Long id;

    private String title;
    private String useSkill;
    private String url_1;
    private String url_2;
    private String url_3;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

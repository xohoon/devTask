package xohoon.devTask.domain.entity.Toy;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ToyDetail {
    @Id @GeneratedValue
    @Column(name = "toy_detail_id")
    private Long id;

    private String toy_subject; // main title
    private String toy_part; // 전문분야
    private String toy_part_personnel; // 분야 모집 인원
    private String toying_day; // 진행 기간
    private String toy_need_skill; // 과제진행 필요한 기술
    private String toy_btn_id; // 버튼 id

    // toyDetail N : 1 toy
    @ManyToOne
    @JoinColumn(name = "toy_id")
    private Toy toy;

    // toyDetail 1 : N toySupport
    @OneToMany(mappedBy = "toyDetail")
    private List<ToySupport> toySupports = new ArrayList<>();
}

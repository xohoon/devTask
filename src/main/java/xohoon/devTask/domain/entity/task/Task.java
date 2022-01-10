package xohoon.devTask.domain.entity.task;

import lombok.*;
import xohoon.devTask.domain.entity.BaseEntity;
import xohoon.devTask.domain.entity.Company;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "task_title", "task_dead_day"})
public class Task extends BaseEntity implements Serializable {
    /*
    * 과제 메인 테이블
    * */
    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;
    private String task_title; // 제목
    private String task_dead_day; // 마감 날짜
    private int tasking_status; // 마감 형태

    // task N : 1 company
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_id")
    private Company company;

    // task 1 : N taskDetail
    @OneToMany(mappedBy = "task")
    private List<TaskDetail> taskDetails = new ArrayList<>();
}

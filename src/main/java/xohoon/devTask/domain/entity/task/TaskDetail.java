package xohoon.devTask.domain.entity.task;

import lombok.*;
import xohoon.devTask.domain.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "task_subject", "task_part"})
public class TaskDetail extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "td_id")
    private Long id;

    private String task_subject; // main title
    private String task_part; // 전문분야
    private String task_part_personnel; // 분야 모집 인원
    private String tasking_day; // 진행 기간
    private String task_need_skill; // 과제진행 필요한 기술
    private String task_btn_id; // 버튼 id

    // taskDetail N : 1 task
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    // taskDetail 1 : N taskSupport
    @OneToMany(mappedBy = "taskDetails", fetch = FetchType.EAGER)
    private Set<TaskSupport> taskSupports = new HashSet<>();
}

package xohoon.devTask.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xohoon.devTask.domain.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetail extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "td_id")
    private Long id;

    private String task_subject; // main title
    private String task_part; // 전문분야
    private String task_part_personnel; // 분야 모집 인원
    private String tasking_day; // 진행 기간
    private String task_need_skill; // 과제진행 필요한 기술

    // Task 1 : N TaskDetail
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    // TaskDetail 1 : N TaskSupport
    @OneToMany(mappedBy = "taskDetails")
    private List<TaskSupport> taskSupports = new ArrayList<>();
}

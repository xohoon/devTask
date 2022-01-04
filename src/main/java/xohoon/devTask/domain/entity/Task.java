package xohoon.devTask.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@Data
public class Task {

    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;
    private String task_subject; // 제목
    private String task_parts; // 모집 분야
    private String task_part_personnel; // 모집 인원
    private int tasking_day; // 진행기간
    private String task_skill_content; // 상세 기술
    private String task_dead_day; // 마감 날짜
    private int tasking_status; // 마감 형태

    // common
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;

    @OneToMany(mappedBy = "tasks")
    private List<TaskSupport> supportTasks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

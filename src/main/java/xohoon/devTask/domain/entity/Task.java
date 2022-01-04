package xohoon.devTask.domain.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Long id;
    private String subject;
    private int task_day;
    private String detail_content;

    // common
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;

    @OneToMany(mappedBy = "tasks")
    private List<TaskSupport> supportMembers = new ArrayList<>();
}

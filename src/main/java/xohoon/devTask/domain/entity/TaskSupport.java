package xohoon.devTask.domain.entity;

import javax.persistence.*;

@Entity
public class TaskSupport {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task tasks;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member members;
}

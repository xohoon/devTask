package xohoon.devTask.domain.entity.task;

import lombok.*;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"taskDetails", "members"},of = {"id", "task_subject", "task_part"})
public class TaskSupport {
    @Id @GeneratedValue
    @Column(name = "ts_id")
    private Long id;

    /*
    * TaskDetail N : M Member 이므로
    * TaskSupport N : 1 TaskDetail
    * TaskSupport N : 1 Member
    * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "td_id")
    private TaskDetail taskDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member members;
}

package xohoon.devTask.domain.entity.task;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"id", "taskDetails", "members"})
public class TaskSupport {
    @Id @GeneratedValue
    @Column(name = "task_support_id")
    private Long id;

    @ColumnDefault("0")
    private int support_status; // company -> dev 과제 요청 상태
    @ColumnDefault("0")
    private int success_status; // dev -> dev 과제 완료 상태

//    private Long td_id;
//    private Long member_id;

    /*
    * TaskDetail N : M Member 이므로
    * TaskSupport N : 1 TaskDetail
    * TaskSupport N : 1 Member
    * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_detail_id")
    private TaskDetail taskDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

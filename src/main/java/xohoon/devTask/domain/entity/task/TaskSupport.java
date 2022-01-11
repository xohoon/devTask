package xohoon.devTask.domain.entity.task;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"taskDetails", "members"})
public class TaskSupport {
    @Id @GeneratedValue
    @Column(name = "ts_id")
    private Long ts_id;

    @ColumnDefault("0")
    private int support_status;

//    private Long td_id;
//    private Long member_id;

    /*
    * TaskDetail N : M Member 이므로
    * TaskSupport N : 1 TaskDetail
    * TaskSupport N : 1 Member
    * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "td_id")
    private TaskDetail taskDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

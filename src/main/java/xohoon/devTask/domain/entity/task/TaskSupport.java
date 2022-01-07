package xohoon.devTask.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xohoon.devTask.domain.entity.Member;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

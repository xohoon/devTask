package xohoon.devTask.domain.entity.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xohoon.devTask.domain.entity.Company;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    /*
    * 과제 메인 테이블
    * */
    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;
    private String task_subject; // 제목
    private String task_dead_day; // 마감 날짜
    private int tasking_status; // 마감 형태

    // common
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;

    /*
     * company 1 : N task
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_id")
    private Company company;

}

package xohoon.devTask.domain.entity.task;

import lombok.*;
import xohoon.devTask.domain.entity.BaseEntity;
import xohoon.devTask.domain.entity.Company;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity implements Serializable {
    /*
    * 과제 메인 테이블
    * */
    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;
    private String task_title; // 제목
    private String task_dead_day; // 마감 날짜
    private int tasking_status; // 마감 형태

    // company 1 : N task
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_id")
    private Company company;
}

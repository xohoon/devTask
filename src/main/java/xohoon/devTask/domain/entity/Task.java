package xohoon.devTask.domain.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Task {

    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;
    private String subject;
    private int task_day;
    private String detail_content;

    // common
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;

}

package xohoon.devTask.domain.entity;

import lombok.*;
import xohoon.devTask.domain.entity.task.Task;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "com_id")
    private Long id;
    private String company;
    private String site_url;
    private String welfare;
    private String kit;
    private int personnel;
    private String salary;
    private String address;

    // member 1 : 1 company
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member = new Member();

    // company 1 : N task
    @OneToMany(mappedBy = "company")
    private List<Task> tasks = new ArrayList<>();
}

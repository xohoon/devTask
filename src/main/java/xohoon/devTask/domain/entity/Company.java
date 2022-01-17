package xohoon.devTask.domain.entity;

import lombok.*;
import xohoon.devTask.domain.entity.task.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "company", "address"})
public class Company extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;
    private String company;
    private String site_url;
    private String welfare;
    private String kit;
    private int personnel;
    private String salary;
    private String address;

    // company 1 : 1 member
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member = new Member();
}

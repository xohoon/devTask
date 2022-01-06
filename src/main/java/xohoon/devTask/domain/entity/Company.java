package xohoon.devTask.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

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

    // common
    private String createDate;
    private String lastModifiedDate;
    private Long lastModifiedMemberId;

    /*
    * member 1 : 1 company
    * */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

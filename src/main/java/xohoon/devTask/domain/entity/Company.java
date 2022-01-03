package xohoon.devTask.domain.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
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
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long lastModifiedMemberId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
}

package xohoon.devTask.domain.entity;

import lombok.*;
import xohoon.devTask.domain.entity.Toy.ToySupport;
import xohoon.devTask.domain.entity.admin.Role;
import xohoon.devTask.domain.entity.task.TaskSupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"userRoles"}, of = {"id", "username", "email"})
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private int age;

    /*
    * 권한매핑
    * */
    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "member_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> userRoles = new HashSet<>();

    // member 1 : N taskSupport
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<TaskSupport> taskSupports = new HashSet<>();

    // member 1 : N toySupport
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<ToySupport> toySupports = new HashSet<>();


}

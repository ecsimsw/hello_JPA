package helloJPA.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Column(name= "TEAM_ID")
    private long teamId;

    @Enumerated(EnumType.STRING)
    private memberType memberType;

    @Column(name ="AGE_member")
    private Long age;
}
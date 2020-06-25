package helloJPA.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {
    @Id
    private Long id;
    @Column(nullable = false, insertable = false, length=20)
    private String name;

    @Enumerated(EnumType.STRING)
    private memberType memberType;

    @Column(name ="AGE_member")
    private Long age;
}

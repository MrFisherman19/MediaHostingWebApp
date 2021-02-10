package mrfisherman.hosting.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Authority() {
    }

    public Authority(String role_user) {
        this.name = role_user;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}

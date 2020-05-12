package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "User")
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String name;

    private String password;

    @ManyToMany(mappedBy = "subscribers",fetch = FetchType.EAGER)
    private Set<Forum> subscribedForums = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Forum> getSubscribedForums() {
        return subscribedForums;
    }

    public void setSubscribedForums(Set<Forum> subscribedForums) {
        this.subscribedForums = subscribedForums;
    }

}

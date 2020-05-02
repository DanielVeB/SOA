package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "library")
public class Library implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;





    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "library")
public class Library implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Reader readerId;

    @ManyToOne
    private Book bookId;

    private Date borrowDate;

    private Date returnDate;




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Propagator extends User {
    @ElementCollection
    @CollectionTable(name = "listOfTakes")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Take> takes = new ArrayList<>();
}

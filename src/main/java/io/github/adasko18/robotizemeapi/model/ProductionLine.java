package io.github.adasko18.robotizemeapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "production_line")
public class ProductionLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String type;

    @OneToMany(
            mappedBy = "productionLine",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Robot> robots;

    public ProductionLine() {
    }

    public ProductionLine(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Robot> getRobots() {
        return robots;
    }

    public void setRobots(Set<Robot> robots) {
        this.robots = robots;
    }

    @Override
    public String toString() {
        return "ProductionLine{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", robots=" + robots +
                '}';
    }
}

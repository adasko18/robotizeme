package io.github.adasko18.robotizemeapi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gluing")
public class GluingRobot extends Robot {

    @Column(name = "glue_level")
    private int glueLevel;

    public GluingRobot() {
        super();
    }

    public GluingRobot(String name, double maxRange, double maxLoad, int glueLevel) {
        super(name, maxRange, maxLoad);
        this.glueLevel = glueLevel;
    }

    public int getGlueLevel() {
        return glueLevel;
    }

    public void setGlueLevel(int glueLevel) {
        if((glueLevel > this.glueLevel && glueLevel <= 100)
            || (glueLevel < this.glueLevel && glueLevel >= 0)) {
            this.glueLevel = glueLevel;
        }
    }

    @Override
    public String toString() {
        return "WeldingRobot{" +
                "gunStatus='" + glueLevel + '\'' +
                '}';
    }
}

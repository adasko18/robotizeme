package io.github.adasko18.robotizemeapi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("welding")
public class WeldingRobot extends Robot {

    private String gunStatus;

    public WeldingRobot() {
        super();
    }

    public WeldingRobot(String name, double maxRange, double maxLoad, String gunStatus) {
        super(name, maxRange, maxLoad);
        this.gunStatus = gunStatus;
    }

    public String getGunStatus() {
        return gunStatus;
    }

    public void setGunStatus(String gunStatus) {
        this.gunStatus = gunStatus;
    }

    @Override
    public String toString() {
        return "WeldingRobot{" +
                "gunStatus='" + gunStatus + '\'' +
                '}';
    }

}

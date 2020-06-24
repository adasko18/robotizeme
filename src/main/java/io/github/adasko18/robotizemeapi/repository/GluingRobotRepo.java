package io.github.adasko18.robotizemeapi.repository;

import io.github.adasko18.robotizemeapi.model.GluingRobot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GluingRobotRepo extends JpaRepository<GluingRobot,String> {
    List<GluingRobot> findByProductionLineId(String lineId);
}

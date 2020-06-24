package io.github.adasko18.robotizemeapi.repository;

import io.github.adasko18.robotizemeapi.model.WeldingRobot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeldingRobotRepo extends JpaRepository<WeldingRobot,String> {
    List<WeldingRobot> findByProductionLineId(String lineId);
}

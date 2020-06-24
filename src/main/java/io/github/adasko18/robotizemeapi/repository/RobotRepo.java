package io.github.adasko18.robotizemeapi.repository;

import io.github.adasko18.robotizemeapi.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RobotRepo extends JpaRepository<Robot,String> {
    List<Robot> findByProductionLineId(String lineId);
}

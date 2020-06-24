package io.github.adasko18.robotizemeapi.service;

import io.github.adasko18.robotizemeapi.model.Robot;
import io.github.adasko18.robotizemeapi.repository.ProductionLineRepo;
import io.github.adasko18.robotizemeapi.repository.RobotRepo;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotService {
    private RobotRepo robotRepo;
    private ProductionLineRepo productionLineRepo;

    public RobotService(RobotRepo robotRepo, ProductionLineRepo productionLineRepo) {
        this.robotRepo = robotRepo;
        this.productionLineRepo = productionLineRepo;
    }

    public List<Robot> findInLineRobotsByRobotId(String lineId) throws NotFoundException {
        if (!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Production line not found");
        }
        return robotRepo.findByProductionLineId(lineId);
    }

    public Robot addRobot(String lineId, Robot robot) throws NotFoundException {
        return productionLineRepo.findById(lineId)
                .map(productionLine -> {
                    robot.setProductionLine(productionLine);
                    return robotRepo.save(robot);
                }).orElseThrow(() -> new NotFoundException("Robot not found"));
    }

    public Robot updateRobot(String lineId, String robotId, Robot updatedRobot) throws NotFoundException {
        if(!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Production line not found!");
        }
        return robotRepo.findById(robotId)
                .map(robot -> {
                    robot.setName(updatedRobot.getName());
                    robot.setMaxRange(updatedRobot.getMaxRange());
                    robot.setMaxLoad(updatedRobot.getMaxLoad());
                    return robotRepo.save(robot);
                }).orElseThrow(() -> new NotFoundException("Robot not found!"));
    }

    public String delete(String lineId, String robotId) throws NotFoundException {
        if(!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Production line not found!");
        }
        return robotRepo.findById(robotId)
                .map(robot -> {
                    robotRepo.delete(robot);
                    return "Robot with id " + robotId + " was deleted";
                }).orElseThrow(() -> new NotFoundException("Robot not found with id " + robotId));
    }
}

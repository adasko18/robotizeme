package io.github.adasko18.robotizemeapi.service;

import io.github.adasko18.robotizemeapi.model.WeldingRobot;
import io.github.adasko18.robotizemeapi.repository.ProductionLineRepo;
import io.github.adasko18.robotizemeapi.repository.WeldingRobotRepo;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeldingRobotService {
    private WeldingRobotRepo weldingRobotRepo;
    private ProductionLineRepo productionLineRepo;

    public WeldingRobotService(WeldingRobotRepo weldingRobotRepo, ProductionLineRepo productionLineRepo) {
        this.weldingRobotRepo = weldingRobotRepo;
        this.productionLineRepo = productionLineRepo;
    }

    public List<WeldingRobot> findInLineRobotsByRobotId(String lineId) throws NotFoundException {
        if (!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Robots not found");
        }
        return weldingRobotRepo.findByProductionLineId(lineId);
    }

    public WeldingRobot addRobot(String lineId, WeldingRobot weldingRobot) throws NotFoundException {
        return productionLineRepo.findById(lineId)
                .map(productionLine -> {
                    weldingRobot.setProductionLine(productionLine);
                    return weldingRobotRepo.save(weldingRobot);
                }).orElseThrow(() -> new NotFoundException("Robot not found"));
    }

    public WeldingRobot updateRobot(String lineId, String robotId, WeldingRobot updatedWeldingRobot) throws NotFoundException {
        if(!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Production line not found!");
        }
        return weldingRobotRepo.findById(robotId)
                .map(robot -> {
                    robot.setName(updatedWeldingRobot.getName());
                    robot.setMaxRange(updatedWeldingRobot.getMaxRange());
                    robot.setMaxLoad(updatedWeldingRobot.getMaxLoad());
                    return weldingRobotRepo.save(robot);
                }).orElseThrow(() -> new NotFoundException("Robot not found!"));
    }

    public String delete(String lineId, String robotId) throws NotFoundException {
        if(!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Production line not found!");
        }
        return weldingRobotRepo.findById(robotId)
                .map(robot -> {
                    weldingRobotRepo.delete(robot);
                    return "Robot with id " + robotId + " was deleted";
                }).orElseThrow(() -> new NotFoundException("Robot not found with id " + robotId));
    }
}

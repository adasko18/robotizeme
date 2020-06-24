package io.github.adasko18.robotizemeapi.service;

import io.github.adasko18.robotizemeapi.model.GluingRobot;
import io.github.adasko18.robotizemeapi.repository.GluingRobotRepo;
import io.github.adasko18.robotizemeapi.repository.ProductionLineRepo;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GluingRobotService {
    private GluingRobotRepo gluingRobotRepo;
    private ProductionLineRepo productionLineRepo;


    public GluingRobotService(GluingRobotRepo gluingRobotRepo, ProductionLineRepo productionLineRepo) {
        this.gluingRobotRepo = gluingRobotRepo;
        this.productionLineRepo = productionLineRepo;
    }

    public List<GluingRobot> findInLineRobotsByRobotId(String lineId) throws NotFoundException {
        if (!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Robots not found");
        }
        return gluingRobotRepo.findByProductionLineId(lineId);
    }

    public GluingRobot addRobot(String lineId, GluingRobot gluingRobot) throws NotFoundException {
        return productionLineRepo.findById(lineId)
                .map(productionLine -> {
                    gluingRobot.setProductionLine(productionLine);
                    return gluingRobotRepo.save(gluingRobot);
                }).orElseThrow(() -> new NotFoundException("Robot not found"));
    }

    public GluingRobot updateRobot(String lineId, String robotId, GluingRobot updatedGluingRobot) throws NotFoundException {
        if(!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Production line not found!");
        }
        return gluingRobotRepo.findById(robotId)
                .map(robot -> {
                    robot.setName(updatedGluingRobot.getName());
                    robot.setMaxRange(updatedGluingRobot.getMaxRange());
                    robot.setMaxLoad(updatedGluingRobot.getMaxLoad());
                    return gluingRobotRepo.save(robot);
                }).orElseThrow(() -> new NotFoundException("Robot not found!"));
    }

    public String delete(String lineId, String robotId) throws NotFoundException {
        if(!productionLineRepo.existsById(lineId)) {
            throw new NotFoundException("Production line not found!");
        }
        return gluingRobotRepo.findById(robotId)
                .map(robot -> {
                    gluingRobotRepo.delete(robot);
                    return "Robot with id " + robotId + " was deleted";
                }).orElseThrow(() -> new NotFoundException("Robot not found with id " + robotId));
    }
}

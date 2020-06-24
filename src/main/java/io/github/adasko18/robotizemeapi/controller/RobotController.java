package io.github.adasko18.robotizemeapi.controller;

import io.github.adasko18.robotizemeapi.model.Robot;
import io.github.adasko18.robotizemeapi.service.RobotService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class RobotController {

    private RobotService robotService;

    @Autowired
    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @GetMapping("/lines/{lineId}/robots")
    public List<Robot> getInLineRobotsByRobotId(@PathVariable String lineId) {
        try {
            return robotService.findInLineRobotsByRobotId(lineId);
        } catch (NotFoundException e) {
            return Collections.emptyList();
        }
    }

    @PostMapping("/lines/{lineId}/robots")
    public Robot addRobot(@PathVariable String lineId,@RequestBody Robot robot) {
        try {
            return robotService.addRobot(lineId, robot);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @PutMapping("/lines/{lineId}/robots/{robotId}")
    public Robot updateRobot(@PathVariable String lineId, @PathVariable String robotId, @RequestBody Robot robot) {
        try {
            return robotService.updateRobot(lineId, robotId, robot);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @DeleteMapping("/lines/{lineId}/robots/{robotId}")
    public String deleteRobot(@PathVariable String lineId, @PathVariable String robotId) {
        try {
            return robotService.delete(lineId, robotId);
        } catch (NotFoundException e) {
            return "";
        }
    }
}

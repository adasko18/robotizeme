package io.github.adasko18.robotizemeapi.controller;

import io.github.adasko18.robotizemeapi.model.GluingRobot;
import io.github.adasko18.robotizemeapi.service.GluingRobotService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class GluingRobotController {

    private GluingRobotService gluingRobotService;

    @Autowired
    public GluingRobotController(GluingRobotService gluingRobotService) {
        this.gluingRobotService = gluingRobotService;
    }

    @GetMapping("/lines/{lineId}/gluingrobots")
    public List<GluingRobot> getInLineRobotsByRobotId(@PathVariable String lineId) {
        try {
            return gluingRobotService.findInLineRobotsByRobotId(lineId);
        } catch (NotFoundException e) {
            return Collections.emptyList();
        }
    }

    @PostMapping("/lines/{lineId}/gluingrobots")
    public GluingRobot addRobot(@PathVariable String lineId,@RequestBody GluingRobot gluingRobot) {
        try {
            return gluingRobotService.addRobot(lineId, gluingRobot);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @PutMapping("/lines/{lineId}/gluingrobots/{robotId}")
    public GluingRobot updateRobot(@PathVariable String lineId, @PathVariable String robotId, @RequestBody GluingRobot gluingRobot) {
        try {
            return gluingRobotService.updateRobot(lineId, robotId, gluingRobot);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @DeleteMapping("/lines/{lineId}/gluingrobots/{robotId}")
    public String deleteRobot(@PathVariable String lineId, @PathVariable String robotId) {
        try {
            return gluingRobotService.delete(lineId, robotId);
        } catch (NotFoundException e) {
            return "";
        }
    }
}

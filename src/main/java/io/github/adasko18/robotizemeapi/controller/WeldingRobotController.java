package io.github.adasko18.robotizemeapi.controller;

import io.github.adasko18.robotizemeapi.model.WeldingRobot;
import io.github.adasko18.robotizemeapi.service.WeldingRobotService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class WeldingRobotController {

    private WeldingRobotService weldingRobotService;

    @Autowired
    public WeldingRobotController(WeldingRobotService weldingRobotService) {
        this.weldingRobotService = weldingRobotService;
    }

    @GetMapping("/lines/{lineId}/weldingrobots")
    public List<WeldingRobot> getInLineRobotsByRobotId(@PathVariable String lineId) {
        try {
            return weldingRobotService.findInLineRobotsByRobotId(lineId);
        } catch (NotFoundException e) {
            return Collections.emptyList();
        }
    }

    @PostMapping("/lines/{lineId}/weldingrobots")
    public WeldingRobot addRobot(@PathVariable String lineId,@RequestBody WeldingRobot weldingRobot) {
        try {
            return weldingRobotService.addRobot(lineId, weldingRobot);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @PutMapping("/lines/{lineId}/weldingrobots/{robotId}")
    public WeldingRobot updateRobot(@PathVariable String lineId, @PathVariable String robotId, @RequestBody WeldingRobot weldingRobot) {
        try {
            return weldingRobotService.updateRobot(lineId, robotId, weldingRobot);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @DeleteMapping("/lines/{lineId}/weldingrobots/{robotId}")
    public String deleteRobot(@PathVariable String lineId, @PathVariable String robotId) {
        try {
            return weldingRobotService.delete(lineId, robotId);
        } catch (NotFoundException e) {
            return "";
        }
    }
}

package io.github.adasko18.robotizemeapi.controller;

import io.github.adasko18.robotizemeapi.model.ProductionLine;
import io.github.adasko18.robotizemeapi.service.ProductionLineService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProductionLineController {

    private ProductionLineService productionLineService;

    @Autowired
    public ProductionLineController(ProductionLineService productionLineService) {
        this.productionLineService = productionLineService;
    }

    @GetMapping("/lines")
    public Iterable<ProductionLine> getAll() {
        return productionLineService.findAll();
    }

    @GetMapping("/lines/{id}")
    public ProductionLine getById(@PathVariable String id) {
        return productionLineService.findById(id);
    }

    @PostMapping("/lines")
    public ProductionLine createProductionLine(@RequestBody ProductionLine productionLine) {
        return productionLineService.create(productionLine);
    }

    @PutMapping("/lines/{id}")
    public ProductionLine updateProductionLine(@PathVariable String id, @RequestBody ProductionLine productionLine) {
        try {
            return productionLineService.update(id,productionLine);
        } catch (NotFoundException e) {
            return null;
        }
    }

    @DeleteMapping("/lines/{id}")
    public void deleteProductionLine(@PathVariable String id) {
        try {
           productionLineService.delete(id);
        } catch (NotFoundException e) {

        }
    }
}

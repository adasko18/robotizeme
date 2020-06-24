package io.github.adasko18.robotizemeapi.service;

import io.github.adasko18.robotizemeapi.model.ProductionLine;
import io.github.adasko18.robotizemeapi.repository.ProductionLineRepo;
import io.github.adasko18.robotizemeapi.service.exceptions.ProductionLineException;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class ProductionLineService {
    private ProductionLineRepo productionLineRepo;

    @PostConstruct
    public void testProductionLine() {
        ProductionLine productionLine = new ProductionLine("STA1", "welding");
        System.out.println(productionLine);
        productionLineRepo.save(productionLine);
    }

    public ProductionLineService(ProductionLineRepo productionLineRepo) {
        this.productionLineRepo = productionLineRepo;
    }

    public ProductionLine findById(String id) {
        Optional<ProductionLine> productionLine = productionLineRepo.findById(id);
        if (productionLine.isPresent()) {
            return productionLine.get();
        } else {
            throw new ProductionLineException();
        }
    }

    public Iterable<ProductionLine> findAll() {
        return productionLineRepo.findAll();
    }

    public ProductionLine create(ProductionLine productionLine) {
        return productionLineRepo.save(productionLine);
    }

    public ProductionLine update(String id, ProductionLine productionLineUpdated) throws NotFoundException {
        return productionLineRepo.findById(id)
                .map(productionLine -> {
                    productionLine.setName(productionLineUpdated.getName());
                    productionLine.setType(productionLineUpdated.getType());
                    return productionLineRepo.save(productionLine);
                }).orElseThrow(() -> new NotFoundException("Production line not found with id " + id));
    }

    public void delete(String id) throws NotFoundException {
        if (productionLineRepo.findById(id).isPresent()) {
            productionLineRepo.delete(productionLineRepo.findById(id).get());
        } else {
            throw new NotFoundException("Production line not found with id " + id);
        }
    }
}

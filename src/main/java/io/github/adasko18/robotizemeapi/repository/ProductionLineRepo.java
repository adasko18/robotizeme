package io.github.adasko18.robotizemeapi.repository;

import io.github.adasko18.robotizemeapi.model.ProductionLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionLineRepo extends JpaRepository<ProductionLine,String> {

}

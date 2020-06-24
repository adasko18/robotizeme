package io.github.adasko18.robotizemeapi.service;

import io.github.adasko18.robotizemeapi.model.ProductionLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest
class ProductionLineServiceTest {

    private final static  String URI_ALL = "/api/lines";
    private final static  String URI_FIRST = "/api/lines/1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductionLineService productionLineService;

    @MockBean
    private GluingRobotService gluingRobotService;

    @MockBean
    private RobotService robotService;

    @MockBean
    private  WeldingRobotService weldingRobotService;

    @Test
    void shouldGetProductionLineWithIdOne() throws Exception {
        //given
        ProductionLine productionLine = new ProductionLine();
        productionLine.setId("1");
        productionLine.setName("Line");
        productionLine.setType("Test");
        given(productionLineService.findById(productionLine.getId())).willReturn(productionLine);
        System.out.println(productionLine);
        //then
        //when
        mockMvc.perform(get(URI_FIRST)).andExpect(status().isOk())
                .andExpect(content().json("{'id':'1','name':'Line','type':'Test'}"));
    }

    @Test
    void shouldGetAllProductionLines() throws Exception {
        //given
        ProductionLine productionLine = new ProductionLine();
        productionLine.setId("1");
        productionLine.setName("Line");
        productionLine.setType("Test");
        List<ProductionLine> productionLines = Arrays.asList(productionLine);
        given(productionLineService.findAll()).willReturn(productionLines);
        //then
        //when
        mockMvc.perform(get(URI_ALL)).andExpect(status().isOk())
                .andExpect(content().json("[{'id':'1','name':'Line','type':'Test'}]"));
    }

}

package io.github.adasko18.robotizemeapi;

import io.github.adasko18.robotizemeapi.model.ProductionLine;
import io.github.adasko18.robotizemeapi.service.GluingRobotService;
import io.github.adasko18.robotizemeapi.service.ProductionLineService;
import io.github.adasko18.robotizemeapi.service.RobotService;
import io.github.adasko18.robotizemeapi.service.WeldingRobotService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class IntegrationTest {

    private final static  String URI_ALL = "/api/lines";
    private final static  String URI_ID = "/api/lines/{id}";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private ProductionLineService productionLineService;

    @MockBean
    private GluingRobotService gluingRobotService;

    @MockBean
    private RobotService robotService;

    @MockBean
    private WeldingRobotService weldingRobotService;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void shouldGetProductionLineWithIdOne() throws Exception {
        //given
        ProductionLine productionLine = new ProductionLine();
        productionLine.setId("1");
        productionLine.setName("Line");
        productionLine.setType("Test");
        given(productionLineService.findById(productionLine.getId())).willReturn(productionLine);
        //then
        //when
        mockMvc.perform(MockMvcRequestBuilders.get(URI_ID,1)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}

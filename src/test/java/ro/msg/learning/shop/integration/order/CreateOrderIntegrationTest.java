package ro.msg.learning.shop.integration.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.integration.IntegrationTestConfig;
import ro.msg.learning.shop.models.dto.OrderDto;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.order.OrderResponse;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.unit.strategies.order.OrderStrategy;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigWebContextLoader.class,
        classes = {
                IntegrationTestConfig.class,
                OrderStrategy.class
        }
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class
)
public class CreateOrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createOrderSuccess_andReturnStatus201() throws Exception {
        OrderDto newOrder = this.objectMapper.readValue(ResourceUtils.getFile("classpath:test/integration/order/new/newOrder_success.json"), OrderDto.class);
        String bodyContent = this.objectMapper.writeValueAsString(newOrder);

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/orders/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent)
        )
        .andDo(print())
        .andExpect(status().isCreated());

        MvcResult result = resultActions.andReturn();
        String content = result.getResponse().getContentAsString();

        OrderResponse response = objectMapper.readValue(content, OrderResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(StatusCode.CREATED);
        assertThat(response.getMessage()).isEqualTo(ApplicationConstants.ORDER_CREATE_SUCCESS);
    }

    @Test
    public void createOrderFailure_andReturnStatus404() throws Exception {
        OrderDto newOrder = this.objectMapper.readValue(ResourceUtils.getFile("classpath:test/integration/order/new/newOrder_failure.json"), OrderDto.class);
        String bodyContent = this.objectMapper.writeValueAsString(newOrder);

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/orders/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyContent)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        MvcResult result = resultActions.andReturn();
        String content = result.getResponse().getContentAsString();

        OrderResponse response = objectMapper.readValue(content, OrderResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(StatusCode.NOT_FOUND);
        assertThat(response.getMessage()).isEqualTo(ApplicationConstants.NO_STOCKS_FOUND);
    }

}

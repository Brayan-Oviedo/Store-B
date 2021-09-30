package co.store.application.rest;

import co.store.application.request.ProductRequest;
import co.store.application.request.order.OrderSaleRequest;
import co.store.domain.exception.Messages;
import co.store.domain.exception.ResException;
import co.store.domain.model.Client;
import co.store.domain.model.order.Order;
import co.store.domain.model.order.OrderSale;
import co.store.domain.service.impl.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderControllerTestIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OrderService service;

    private Long id;
    private OrderSaleRequest order ;
    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        order = new OrderSaleRequest(new ArrayList<ProductRequest>(), null);
        id = service.createOrderSale(order).getId();
    }

    @Test
    void testGetOrderByIdAllOk() throws Exception {
        MvcResult result = mvc.perform(
                get("/order/getOrder/".concat(id.toString())).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        OrderSale order = objectMapper.readValue(resultContent, OrderSale.class);
        assertSame(id, order.getId());
    }

    @Test
    void testGetOrderByIdWithClientNoExisting() throws Exception {
        MvcResult result = mvc.perform(
                        get("/order/getOrder/".concat("-1")).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        ResException exception = objectMapper.readValue(resultContent, ResException.class);
        assertTrue(exception.getMssg().contains(Messages.MESSAGE_ORDER_NO_EXISTING));
    }

    @Test
    void testCreateOrderSaleAllOk() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(order);

        MvcResult result = mvc.perform(
                        post("/order/createOrderSale").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        OrderSale orderSale = objectMapper.readValue(resultContent, OrderSale.class);
        assertSame(order.getProducts().size(), orderSale.getProducts().size());
    }

    @Test
    void testDeleteOrderByIdAllOk() throws Exception {
        mvc.perform(
                delete("/order/deleteOrder/".concat(id.toString())).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void testCancelOrderByIdAllOk() throws Exception {
        mvc.perform(
                        put("/order/cancelOrder/".concat(id.toString())).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testCancelOrderByIdWithOrderNoExisting() throws Exception {
        MvcResult result = mvc.perform(
                        put("/order/cancelOrder/".concat("-1")).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        ResException exception = objectMapper.readValue(resultContent, ResException.class);
        assertTrue(exception.getMssg().contains(Messages.MESSAGE_ORDER_NO_EXISTING));
    }
}
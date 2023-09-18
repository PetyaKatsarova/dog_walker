//package nl.hva.dogwalker.communication.controller;
//
//import nl.hva.dogwalker.business.domain.Customer;
//import nl.hva.dogwalker.business.service.RegistrationService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.mockito.Mockito;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@WebMvcTest(CustomerController.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class CustomerControllerTest {
//
//  @MockBean
//  private RegistrationService registrationService;
//
//  private final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);
//  private final MockMvc server;
//
//  private Customer testCustomer1;
//
//  @Autowired
//  public CustomerControllerTest(MockMvc mockMvc) {
//    super();
//    this.server = mockMvc;
//  }
//
//  @BeforeEach
//  public void initTests() {
//    testCustomer1 = new Customer("D. Duck", "duck@duckstad.nl");
//    testCustomer1.setId(1);
//    Mockito.when(registrationService.findCustomerByIdWithDogs(1)).thenReturn(testCustomer1);
//  }
//
//  @Test
//  void customerRegistrationHandler() {
//  }
//
//  @Test
//  void getCustomerByIdHandler() {
//    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/customer/1");
//    try {
//      ResultActions response = server.perform(request);
//      String responseBody = response
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andDo(MockMvcResultHandlers.print())
//        .andReturn().getResponse().getContentAsString();
//      assertThat(responseBody).startsWith("{").endsWith("}").contains("Duck");
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//    }
//  }
//
//  @Test
//  void addNewDogForCustomerHandler() {
//  }
//}
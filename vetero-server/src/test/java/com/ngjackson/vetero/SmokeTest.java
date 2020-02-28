package com.ngjackson.vetero;

import com.ngjackson.vetero.controller.LocationController;
import com.ngjackson.vetero.controller.UserController;
import com.ngjackson.vetero.controller.WeatherController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SmokeTest {

  @Autowired
  LocationController locationController;

  @Autowired
  UserController userController;

  @Autowired
  WeatherController weatherController;

  @Autowired
  private MockMvc mockMvc;

  @LocalServerPort
  private int port;

  @Test
  public void contextLoads() {
    assertThat(locationController).isNotNull();
    assertThat(userController).isNotNull();
    assertThat(weatherController).isNotNull();
  }

  @Test
  public void controllersShouldReturnStatusCode() throws Exception {

    this.mockMvc.perform(get(TestUtils.getBaseUrl(this.port) + "/locations/"))
        .andExpect(status().isBadRequest()); // non-200 because of missing user ID

    this.mockMvc.perform(get(TestUtils.getBaseUrl(this.port) + "/users/9980"))
        .andExpect(status().isOk());

    this.mockMvc.perform(get(TestUtils.getBaseUrl(this.port) + "/weather/"))
        .andExpect(status().isBadRequest()); // non-200 because of missing user ID

  }

}

package com.ngjackson.vetero;

import com.ngjackson.vetero.controller.UserController;
import com.ngjackson.vetero.models.Location;
import com.ngjackson.vetero.models.User;
import com.ngjackson.vetero.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @MockBean("userRepository")
  private UserRepository userRepository;

  @Autowired
  private MockMvc mockMvc;

  @LocalServerPort
  private int port;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getExistingUserShouldReturnUser() throws Exception {

    Set<Location> locations = new HashSet<>();
    locations.add(new Location(20L, "40514"));
    User bob = new User(10L, "Bob", locations);

    when(userRepository.save(Mockito.any(User.class)))
        .thenReturn(bob);

    this.mockMvc.perform(get(TestUtils.getBaseUrl(this.port) + "/users/12345"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(bob.getId())))
        .andExpect(jsonPath("$.username", is(bob.getUsername())))
        .andExpect(jsonPath("$.locations", hasSize(3)));

    verify(userRepository, VerificationModeFactory.times(1)).save(Mockito.any(User.class));
    reset(userRepository);

  }

}


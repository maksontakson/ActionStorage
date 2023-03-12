package app.just.actionstorage.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.just.actionstorage.common.TestConstants;
import app.just.actionstorage.repository.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnAllUsers() throws Exception {
    this.mockMvc.perform(get(TestConstants.Path.USER_CONTROLLER_POST)).andDo(print())
        .andExpect(status().isOk()).andExpect(content()
            .string(containsString("test_user_first")));
  }
}

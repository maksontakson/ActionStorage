package app.just.actionstorage.controller;

import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTIVE_EMAIL_SOURCE_DTO;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTIVE_PHONE_SOURCE_DTO;
import static app.just.actionstorage.common.TestConstants.Model.DATE;
import static app.just.actionstorage.common.TestConstants.Model.USER_NAME1;
import static app.just.actionstorage.common.TestConstants.Model.VALID_ACTION_TYPE_FINISH_TRANSACTION;
import static app.just.actionstorage.common.TestConstants.Model.VALID_ACTION_TYPE_START_TRANSACTION;
import static app.just.actionstorage.common.TestConstants.Path.ACTION_CONTROLLER_POST;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.just.actionstorage.common.TestConstants;
import app.just.actionstorage.repository.AbstractTest;
import app.just.common.dto.ActionType;
import app.just.common.dto.CreateNewActionRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionControllerTest extends AbstractTest {
  @Autowired
  private MockMvc mockMvc;

  private final CreateNewActionRequestDto actionRequestDto1 = CreateNewActionRequestDto.builder()
      .username(USER_NAME1)
      .type(VALID_ACTION_TYPE_START_TRANSACTION)
      .date(DATE)
      .source(ACTIVE_EMAIL_SOURCE_DTO)
      .build();
  private final CreateNewActionRequestDto actionRequestDto2 = CreateNewActionRequestDto.builder()
      .username(USER_NAME1)
      .type(VALID_ACTION_TYPE_FINISH_TRANSACTION)
      .date(DATE)
      .source(ACTIVE_PHONE_SOURCE_DTO)
      .build();

  @Test
  public void shouldSaveListOfActionRequestDto_AndReturnListOfActionDto() throws Exception {
    this.mockMvc.perform(post(ACTION_CONTROLLER_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(List.of(actionRequestDto1, actionRequestDto2))))
        .andDo(print())
        .andExpect(status().isOk()).andExpect(content().string(containsString("test_user_first")));
  }

  @Test
  public void shouldReturnBadRequest() throws Exception {
    this.mockMvc.perform(post(ACTION_CONTROLLER_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(actionRequestDto1)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

package io.spooncode.blog.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spooncode.blog.sample.domain.User;
import io.spooncode.blog.sample.repository.UserRepository;
import io.spooncode.blog.sample.service.UserService;
import io.spooncode.blog.sample.support.UserDto;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleApplication.class)
@WebAppConfiguration
public class SampleApplicationTests {

    @Autowired private WebApplicationContext wac;
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private User user;


    @Test
    public void testCreateUser() throws Exception {
        // given
        UserDto.Request requestUser = modelMapper.map(user, UserDto.Request.class);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestUser)));

        // then
        resultActions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is(requestUser.getUsername())));
    }

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        user = new User();
        user.setUsername("woniper");
        user.setPassword("1234");
        user.setFirstName("kyungWon");
        user.setLastName("lee");
        user.setJoinDate(Calendar.getInstance().getTime());
    }

}

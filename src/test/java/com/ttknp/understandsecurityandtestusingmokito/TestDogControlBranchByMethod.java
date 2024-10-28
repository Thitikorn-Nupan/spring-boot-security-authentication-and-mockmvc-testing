package com.ttknp.understandsecurityandtestusingmokito;

import com.ttknp.understandsecurityandtestusingmokito.configuration.SecurityConfig;
import com.ttknp.understandsecurityandtestusingmokito.controller.DogControl;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

// *** for mocking req/res
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// ** Test a Secured Spring Web MVC Endpoint with MockMvc
@Import(SecurityConfig.class) // import your secure config
@WebMvcTest(DogControl.class) // import your control
public class TestDogControlBranchByMethod {

    @Autowired
    private MockMvc mvc;
    private Logger log = LoggerFactory.getLogger(TestDogControlBranchByMethod.class);

    @Test
    @WithMockUser(username="user",password = "12345",authorities={"read"}) //
    void shouldReturn403Forbidden() throws Exception {
        mvc.perform(get("/api/dogs"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="admin",password = "12345",authorities={"read","write"}) //
    void shouldReturn202Accepted() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/dogs"))
                .andExpect(status().isAccepted())
                .andReturn();

        log.info("mvcResult.getResponse() {}",mvcResult.getResponse().getContentAsString());
    }

}

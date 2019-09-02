package com.github.peacetrue.template.modules.demo;

import com.github.peacetrue.spring.util.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestControllerTemplateAutoConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles("template-controller-test")
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static MultiValueMap<String, String> to(Object object) {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        Map<String, Object> map = BeanUtils.map(object);
        params.setAll(map.entrySet().stream().filter(entry -> entry.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue()))));
        return params;
    }

    @Test
    public void add() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/demos")
                        .params(to(DemoServiceImplTest.DEMO_ADD))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(DemoServiceImplTest.DEMO_ADD.getCode()));

    }

    @Test
    public void query() {
    }

    @Test
    public void get() {
    }

    @Test
    public void modify() {
    }

    @Test
    public void delete() {
    }
}
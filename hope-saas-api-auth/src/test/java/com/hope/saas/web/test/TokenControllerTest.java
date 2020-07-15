package com.hope.saas.web.test;

import com.hope.saas.api.auth.HopeSaasApiAuthApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @description: 令牌单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopeSaasApiAuthApplication.class)
@AutoConfigureMockMvc
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 客户端授权模式获取令牌
     *
     * @throws Exception
     */
    @Test
    public void getToken() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("client_id", Collections.singletonList("9ffb86016a0f30e4b7385a10f78e6b45"));
        map.put("client_secret", Collections.singletonList("$2a$10$umurCW4NkRIJlygLGz02xu9ern87YRZ38BXtOjIiK89p.ZeNpvm3."));
        map.put("grant_type", Collections.singletonList("client_credentials"));
        map.put("scope", Collections.singletonList("all"));
        MockHttpServletResponse response = this.mockMvc.perform(
                post("/oauth/token")
                        .params(map)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn().getResponse();
        switch (response.getStatus()) {
            case HttpStatus.SC_OK:
                log.info(response.getContentAsString());
                log.info("客户端授权模式获取令牌---------------->成功（200）");
                break;
            case HttpStatus.SC_UNAUTHORIZED:
                log.info("客户端授权模式获取令牌---------------->失败（401---没有权限，请检查验证信息，账号是否存在、客户端信息）");
                break;
            case HttpStatus.SC_BAD_REQUEST:
                log.info("客户端授权模式获取令牌---------------->失败（400---请求失败，请检查密码是否正确）");
                break;
            default:
                log.info("客户端授权模式获取令牌---------------->失败（{}---未知结果）", response.getStatus());
                break;
        }
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK);
    }

}

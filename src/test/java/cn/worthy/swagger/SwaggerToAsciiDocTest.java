package cn.worthy.swagger;

import cn.worthy.swagger.categories.SwaggerCtg;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

import javax.annotation.Resource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author:yaowenxi
 * Date: 2018/1/23
 */
@RunWith(SpringRunner.class)
@Category(SwaggerCtg.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SwaggerToAsciiDocTest {

    @Resource
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {
        String outputDir = System.getProperty("springfox.asciidoc.outputDir", "build/docs/generated/asciidoc");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs")
                .accept(APPLICATION_JSON_UTF8))
                .andDo(Swagger2MarkupResultHandler.outputDirectory(outputDir).build())
                .andExpect(status().isOk());
    }

}

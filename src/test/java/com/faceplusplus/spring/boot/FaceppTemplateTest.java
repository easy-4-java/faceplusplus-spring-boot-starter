package com.faceplusplus.spring.boot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link FaceppTemplate}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class FaceppTemplateTest {

    private FaceppTemplate template;
    private FaceppProperties properties;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OkHttpClient client = new OkHttpClient.Builder().build();
        properties = new FaceppProperties();
        properties.setAppId("testAppId");
        properties.setAppCertificate("testCert");
        FaceppOkHttp3Template okhttp3Template = new FaceppOkHttp3Template(client, objectMapper, properties);
        template = new FaceppTemplate(okhttp3Template, properties);
    }

    @Test
    void opsForFaceset_returnsNonNull() {
        assertThat(template.opsForFaceset()).isNotNull();
    }

    @Test
    void opsForFaceDetect_returnsNonNull() {
        assertThat(template.opsForFaceDetect()).isNotNull();
    }

    @Test
    void getFaceppProperties_returnsProperties() {
        assertThat(template.getFaceppProperties()).isEqualTo(properties);
    }

    @Test
    void getFaceppOkHttp3Template_returnsNonNull() {
        assertThat(template.getFaceppOkHttp3Template()).isNotNull();
    }
}

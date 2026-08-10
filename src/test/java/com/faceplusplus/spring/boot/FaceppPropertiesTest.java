package com.faceplusplus.spring.boot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link FaceppProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class FaceppPropertiesTest {

    @Test
    void defaultValues_areCorrect() {
        FaceppProperties props = new FaceppProperties();
        assertThat(props.getHost()).isEqualTo("https://api-cn.faceplusplus.com");
        assertThat(props.getAppId()).isNull();
        assertThat(props.getAppCertificate()).isNull();
        assertThat(props.getExpirationTimeInSeconds()).isEqualTo(3600);
        assertThat(props.getLoginKey()).isNull();
        assertThat(props.getLoginSecret()).isNull();
        assertThat(props.getOssRegion()).isNull();
        assertThat(props.getViewWidth()).isNull();
        assertThat(props.getViewHeight()).isNull();
    }

    @Test
    void settersAndGetters_workCorrectly() {
        FaceppProperties props = new FaceppProperties();
        props.setHost("https://custom.host.com");
        props.setAppId("appId");
        props.setAppCertificate("cert");
        props.setExpirationTimeInSeconds(7200);
        props.setLoginKey("key");
        props.setLoginSecret("secret");
        props.setOssRegion(7);
        props.setViewWidth(1920);
        props.setViewHeight(1080);

        assertThat(props.getHost()).isEqualTo("https://custom.host.com");
        assertThat(props.getAppId()).isEqualTo("appId");
        assertThat(props.getAppCertificate()).isEqualTo("cert");
        assertThat(props.getExpirationTimeInSeconds()).isEqualTo(7200);
        assertThat(props.getLoginKey()).isEqualTo("key");
        assertThat(props.getLoginSecret()).isEqualTo("secret");
        assertThat(props.getOssRegion()).isEqualTo(7);
        assertThat(props.getViewWidth()).isEqualTo(1920);
        assertThat(props.getViewHeight()).isEqualTo(1080);
    }

    @Test
    void prefix_isCorrect() {
        assertThat(FaceppProperties.PREFIX).isEqualTo("faceplusplus.facepp");
    }
}

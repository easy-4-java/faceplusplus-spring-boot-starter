package com.faceplusplus.spring.boot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link FaceppConstant}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class FaceppConstantTest {

    @Test
    void urlChannelUser_isDefined() {
        assertThat(FaceppConstant.URL_CHANNEL_USER).isEqualTo("https://api.agora.io/dev/v1/channel/user/{0}/{1}");
    }

    @Test
    void urlRule_isDefined() {
        assertThat(FaceppConstant.URL_RULE).isEqualTo("https://api.agora.io/dev/v1/kicking-rule");
    }

    @Test
    void recordingUid_isDefined() {
        assertThat(FaceppConstant.RECORDING_UID).isEqualTo("10");
    }

    @Test
    void videoPath_isDefined() {
        assertThat(FaceppConstant.VEIDO_PAHT).isEqualTo("video");
    }
}

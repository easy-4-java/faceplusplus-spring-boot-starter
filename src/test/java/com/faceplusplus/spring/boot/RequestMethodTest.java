package com.faceplusplus.spring.boot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link RequestMethod}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class RequestMethodTest {

    @Test
    void allValues_arePresent() {
        RequestMethod[] values = RequestMethod.values();
        assertThat(values).hasSize(8);
        assertThat(values).contains(
                RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE,
                RequestMethod.OPTIONS, RequestMethod.TRACE
        );
    }

    @Test
    void valueOf_worksCorrectly() {
        assertThat(RequestMethod.valueOf("GET")).isEqualTo(RequestMethod.GET);
        assertThat(RequestMethod.valueOf("POST")).isEqualTo(RequestMethod.POST);
    }
}

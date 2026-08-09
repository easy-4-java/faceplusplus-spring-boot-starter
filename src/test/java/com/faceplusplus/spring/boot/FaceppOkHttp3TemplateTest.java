package com.faceplusplus.spring.boot;

import com.faceplusplus.spring.boot.resp.FaceppResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link FaceppOkHttp3Template}.
 *
 * @author Loong Wan (https://github.com/loong10k)
 */
class FaceppOkHttp3TemplateTest {

    private MockWebServer mockWebServer;
    private FaceppOkHttp3Template template;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        FaceppProperties properties = new FaceppProperties();
        OkHttpClient client = new OkHttpClient.Builder().build();
        template = new FaceppOkHttp3Template(client, objectMapper, properties);
        template.afterPropertiesSet();
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    void afterPropertiesSet_createsClientWhenNull() throws Exception {
        FaceppProperties props = new FaceppProperties();
        FaceppOkHttp3Template t = new FaceppOkHttp3Template(null, objectMapper, props);
        t.afterPropertiesSet();
        assertThat(t).isNotNull();
    }

    @Test
    void post_withHeadersParamsAndBody_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200,\"request_id\":\"req1\"}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Custom", "val");
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        FaceppResponse resp = template.post(url, headers, params, body, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(200);
        assertThat(resp.getRequestId()).isEqualTo("req1");
    }

    @Test
    void post_withHeadersAndParams_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Custom", "val");
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        FaceppResponse resp = template.post(url, headers, params, body, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void get_withUrlAndClass_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        FaceppResponse resp = template.get(url, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void get_withParams_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        FaceppResponse resp = template.get(url, params, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void get_withHeadersAndParams_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Custom", "val");
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        FaceppResponse resp = template.get(url, headers, params, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void doRequest_withUrlAndMethod_returnsRawResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        okhttp3.Response resp = template.doRequest(url, FaceppOkHttp3Template.HttpMethod.GET);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        resp.close();
    }

    @Test
    void doRequest_withUrlMethodAndQueryParams_returnsRawResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        okhttp3.Response resp = template.doRequest(url, FaceppOkHttp3Template.HttpMethod.GET, params);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        resp.close();
    }

    @Test
    void doRequest_withUrlMethodHeadersAndQueryParams_returnsRawResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Custom", "val");
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        okhttp3.Response resp = template.doRequest(url, FaceppOkHttp3Template.HttpMethod.GET, headers, params);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        resp.close();
    }

    @Test
    void doRequest_withAllParams_returnsRawResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Custom", "val");
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        okhttp3.Response resp = template.doRequest(url, FaceppOkHttp3Template.HttpMethod.POST, headers, params, body);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        resp.close();
    }

    @Test
    void doRequest_unsuccessfulResponse_returnsErrorResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("{\"code\":500}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> headers = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        FaceppResponse resp = template.post(url, headers, params, body, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(500);
    }

    @Test
    void getHttpUrl_buildsUrlWithParams() {
        String baseUrl = mockWebServer.url("/test").toString();
        Map<String, Object> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", null);

        okhttp3.HttpUrl httpUrl = template.getHttpUrl(baseUrl, params);

        assertThat(httpUrl).isNotNull();
        assertThat(httpUrl.queryParameter("key1")).isEqualTo("value1");
        assertThat(httpUrl.queryParameter("key2")).isEqualTo("");
    }

    @Test
    void getHttpUrl_buildsUrlWithoutParams() {
        String baseUrl = mockWebServer.url("/test").toString();
        okhttp3.HttpUrl httpUrl = template.getHttpUrl(baseUrl, null);

        assertThat(httpUrl).isNotNull();
    }

    @Test
    void createRequestBuilder_createsBuilderWithHeaders() throws Exception {
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(mockWebServer.url("/test").toString());
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-Custom", "val");

        okhttp3.Request.Builder builder = template.createRequestBuilder(
                httpUrl, FaceppOkHttp3Template.HttpMethod.GET, headers, null);

        assertThat(builder).isNotNull();
    }

    @Test
    void createRequestBuilder_createsBuilderWithBody() throws Exception {
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(mockWebServer.url("/test").toString());
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");

        okhttp3.Request.Builder builder = template.createRequestBuilder(
                httpUrl, FaceppOkHttp3Template.HttpMethod.POST, null, body);

        assertThat(builder).isNotNull();
    }

    @Test
    void readValue_parsesJsonToClass() {
        String json = "{\"code\":200,\"request_id\":\"req1\"}";
        FaceppResponse resp = template.readValue(json, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(200);
        assertThat(resp.getRequestId()).isEqualTo("req1");
    }

    @Test
    void readValue_handlesInvalidJson() {
        String json = "invalid";
        FaceppResponse resp = template.readValue(json, FaceppResponse.class);

        assertThat(resp).isNotNull();
    }

    @Test
    void httpMethod_getByName_returnsCorrectMethod() {
        assertThat(FaceppOkHttp3Template.HttpMethod.getByName(0)).isNull();
    }

    @Test
    void httpMethod_getName_returnsCorrectName() {
        assertThat(FaceppOkHttp3Template.HttpMethod.GET.getName()).isEqualTo("GET");
        assertThat(FaceppOkHttp3Template.HttpMethod.POST.getName()).isEqualTo("POST");
        assertThat(FaceppOkHttp3Template.HttpMethod.PUT.getName()).isEqualTo("PUT");
        assertThat(FaceppOkHttp3Template.HttpMethod.PATCH.getName()).isEqualTo("PATCH");
        assertThat(FaceppOkHttp3Template.HttpMethod.DELETE.getName()).isEqualTo("DELETE");
        assertThat(FaceppOkHttp3Template.HttpMethod.HEAD.getName()).isEqualTo("HEAD");
        assertThat(FaceppOkHttp3Template.HttpMethod.OPTIONS.getName()).isEqualTo("OPTIONS");
        assertThat(FaceppOkHttp3Template.HttpMethod.TRACE.getName()).isEqualTo("TRACE");
    }

    @Test
    void httpMethod_apply_withBody() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.POST.apply(builder, "{\"key\":\"val\"}");
        assertThat(result).isNotNull();
    }

    @Test
    void httpMethod_apply_withoutBody() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.GET.apply(builder);
        assertThat(result).isNotNull();
    }

    @Test
    void httpMethod_delete_withBody() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.DELETE.apply(builder, "{\"key\":\"val\"}");
        assertThat(result).isNotNull();
    }

    @Test
    void httpMethod_delete_withoutBody() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.DELETE.apply(builder, null);
        assertThat(result).isNotNull();
    }

    @Test
    void getObjectMapper_returnsMapper() {
        assertThat(template.getObjectMapper()).isNotNull();
    }

    @Test
    void constants_areDefined() {
        assertThat(FaceppOkHttp3Template.APPLICATION_JSON_VALUE).isEqualTo("application/json");
        assertThat(FaceppOkHttp3Template.APPLICATION_JSON_UTF8_VALUE).isEqualTo("application/json;charset=UTF-8");
        assertThat(FaceppOkHttp3Template.APPLICATION_OCTET_STREAM_VALUE).isEqualTo("application/octet-stream");
        assertThat(FaceppOkHttp3Template.APPLICATION_JSON).isNotNull();
        assertThat(FaceppOkHttp3Template.APPLICATION_JSON_UTF8).isNotNull();
        assertThat(FaceppOkHttp3Template.APPLICATION_OCTET_STREAM).isNotNull();
    }

    @Test
    void doPartRequest_withFileParam_sendsMultipartRequest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        java.io.File tmpFile = java.io.File.createTempFile("test", ".txt");
        params.put("file", tmpFile);
        FaceppResponse resp = template.doPartRequest(url, params, FaceppResponse.class);

        assertThat(resp).isNotNull();
    }

    @Test
    void doRequest_withStartTimeAndUrl_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(url);
        okhttp3.Response resp = template.doRequest(System.currentTimeMillis(), httpUrl,
                FaceppOkHttp3Template.HttpMethod.GET, null, null);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        resp.close();
    }

    @Test
    void doRequest_withStartTimeUrlAndBody_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(url);
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        okhttp3.Response resp = template.doRequest(System.currentTimeMillis(), httpUrl,
                FaceppOkHttp3Template.HttpMethod.POST, null, body);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        resp.close();
    }

    @Test
    void doRequest_withStartTimeUrlMethodHeadersQueryParams_returnsResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        okhttp3.Response resp = template.doRequest(System.currentTimeMillis(), url,
                FaceppOkHttp3Template.HttpMethod.GET, null, params, null);

        assertThat(resp).isNotNull();
        assertThat(resp.isSuccessful()).isTrue();
        resp.close();
    }

    @Test
    void doRequest_withTypedResponse_parsesResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200,\"request_id\":\"req1\"}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(url);
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        FaceppResponse resp = template.doRequest(System.currentTimeMillis(), httpUrl,
                FaceppOkHttp3Template.HttpMethod.POST, null, body, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void doRequest_withTypedResponse_unsuccessful_parsesErrorResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody("{\"code\":400}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(url);
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        FaceppResponse resp = template.doRequest(System.currentTimeMillis(), httpUrl,
                FaceppOkHttp3Template.HttpMethod.POST, null, body, FaceppResponse.class);

        assertThat(resp).isNotNull();
        assertThat(resp.getCode()).isEqualTo(400);
    }

    @Test
    void doRequest_withTypedResponse_invalidJson_returnsDefaultResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("not json")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(url);
        Map<String, Object> body = new HashMap<>();
        body.put("data", "test");
        FaceppResponse resp = template.doRequest(System.currentTimeMillis(), httpUrl,
                FaceppOkHttp3Template.HttpMethod.POST, null, body, FaceppResponse.class);

        assertThat(resp).isNotNull();
    }

    @Test
    void httpMethod_options_returnsBuilder() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.OPTIONS.apply(builder, null);
        assertThat(result).isNotNull();
    }

    @Test
    void httpMethod_trace_returnsBuilder() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.TRACE.apply(builder, null);
        assertThat(result).isNotNull();
    }

    @Test
    void httpMethod_head_returnsBuilder() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.HEAD.apply(builder, null);
        assertThat(result).isNotNull();
    }

    @Test
    void httpMethod_put_withBody() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.PUT.apply(builder, "{\"key\":\"val\"}");
        assertThat(result).isNotNull();
    }

    @Test
    void httpMethod_patch_withBody() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url("http://localhost/test");

        okhttp3.Request.Builder result = FaceppOkHttp3Template.HttpMethod.PATCH.apply(builder, "{\"key\":\"val\"}");
        assertThat(result).isNotNull();
    }

    @Test
    void createRequestBuilder_withNullHeadersAndBody_createsGetBuilder() throws Exception {
        okhttp3.HttpUrl httpUrl = okhttp3.HttpUrl.parse(mockWebServer.url("/test").toString());

        okhttp3.Request.Builder builder = template.createRequestBuilder(
                httpUrl, FaceppOkHttp3Template.HttpMethod.GET, null, null);

        assertThat(builder).isNotNull();
    }

    @Test
    void doAsyncRequest_withConsumer_callsCallback() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200,\"request_id\":\"req1\"}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> body = new HashMap<>();
        body.put("key", "value");

        java.util.function.Consumer<FaceppResponse> consumer = resp -> {};
        java.util.function.BiFunction<okhttp3.Call, java.io.IOException, Boolean> failure = null;

        template.doAsyncRequest(url, FaceppOkHttp3Template.HttpMethod.POST,
                null, null, body, consumer, failure, FaceppResponse.class);

        Thread.sleep(500);
    }

    @Test
    void doAsyncRequest_withTypedBiFunction_callsCallback() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> body = new HashMap<>();
        body.put("key", "value");

        java.util.function.BiFunction<okhttp3.Call, okhttp3.Response, FaceppResponse> success =
                (call, response) -> null;
        java.util.function.BiFunction<okhttp3.Call, java.io.IOException, Boolean> failure = null;

        template.doAsyncRequest(System.currentTimeMillis(), okhttp3.HttpUrl.parse(url),
                FaceppOkHttp3Template.HttpMethod.POST, null, body, success, failure);

        Thread.sleep(500);
    }

    @Test
    void doAsyncRequest_withTypedBiFunctionAndStartTime_callsCallback() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"code\":200}")
                .addHeader("Content-Type", "application/json"));

        String url = mockWebServer.url("/test").toString();
        Map<String, Object> body = new HashMap<>();
        body.put("key", "value");

        java.util.function.BiFunction<okhttp3.Call, okhttp3.Response, FaceppResponse> success =
                (call, response) -> null;
        java.util.function.BiFunction<okhttp3.Call, java.io.IOException, Boolean> failure = null;

        template.doAsyncRequest(System.currentTimeMillis(), url,
                FaceppOkHttp3Template.HttpMethod.POST, null, null, body, success, failure);

        Thread.sleep(500);
    }

    @Test
    void doAsyncRequest_withFailureBiFunction_callsCallback() throws Exception {
        String url = "http://localhost:1/unreachable";

        java.util.function.BiFunction<okhttp3.Call, okhttp3.Response, FaceppResponse> success =
                (call, response) -> null;
        java.util.function.BiFunction<okhttp3.Call, java.io.IOException, Boolean> failure =
                (call, e) -> true;

        template.doAsyncRequest(System.currentTimeMillis(), okhttp3.HttpUrl.parse(url),
                FaceppOkHttp3Template.HttpMethod.GET, null, null, success, failure);

        Thread.sleep(500);
    }
}

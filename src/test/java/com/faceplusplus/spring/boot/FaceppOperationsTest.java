package com.faceplusplus.spring.boot;

import com.faceplusplus.spring.boot.req.*;
import com.faceplusplus.spring.boot.resp.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FaceppFaceOperations} and {@link FaceppFacesetOperations}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class FaceppOperationsTest {

    private FaceppFaceOperations faceOps;
    private FaceppFacesetOperations facesetOps;
    private FaceppFacesetAsyncOperations facesetAsyncOps;
    private FaceppOkHttp3Template mockTemplate;
    private FaceppProperties properties;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        properties = new FaceppProperties();
        properties.setAppId("testAppId");
        properties.setAppCertificate("testCert");

        mockTemplate = Mockito.mock(FaceppOkHttp3Template.class);
        when(mockTemplate.getObjectMapper()).thenReturn(objectMapper);

        FaceppTemplate template = new FaceppTemplate(mockTemplate, properties);
        faceOps = new FaceppFaceOperations(template);
        facesetOps = new FaceppFacesetOperations(template);
        facesetAsyncOps = new FaceppFacesetAsyncOperations(template);
    }

    // --- FaceppFaceOperations tests ---

    @Test
    void detectUrl_callsPost() throws IOException {
        FaceDetectResponse expected = new FaceDetectResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceDetectOptions options = FaceDetectOptions.builder()
                .returnLandmark(2).returnAttributes("gender").build();
        FaceDetectResponse resp = faceOps.detectUrl("http://img.url", options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void detectBase64_callsPost() throws IOException {
        FaceDetectResponse expected = new FaceDetectResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceDetectOptions options = FaceDetectOptions.builder()
                .returnLandmark(1).returnAttributes("age").build();
        FaceDetectResponse resp = faceOps.detectBase64("base64data", options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void detectFile_callsDoPartRequest() throws IOException {
        FaceDetectResponse expected = new FaceDetectResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceDetectOptions options = FaceDetectOptions.builder().build();
        File file = new File("/tmp/test.jpg");
        FaceDetectResponse resp = faceOps.detectFile(file, options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void analyze_callsPost() throws IOException {
        FaceAnalyzeResponse expected = new FaceAnalyzeResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceAnalyzeOptions options = FaceAnalyzeOptions.builder()
                .returnLandmark(2).returnAttributes("gender").build();
        FaceAnalyzeResponse resp = faceOps.analyze(new String[]{"token1"}, options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void compareUrl_callsDoPartRequest() throws IOException {
        FaceCompareResponse expected = new FaceCompareResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceCompareResponse resp = faceOps.compareUrl("http://img1", "http://img2");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void compareToken_callsDoPartRequest() throws IOException {
        FaceCompareResponse expected = new FaceCompareResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceCompareResponse resp = faceOps.compareToken("token1", "token2");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void compareBase64_callsDoPartRequest() throws IOException {
        FaceCompareResponse expected = new FaceCompareResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceCompareResponse resp = faceOps.compareBase64("base64_1", "base64_2");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void compareFile_callsDoPartRequest() throws IOException {
        FaceCompareResponse expected = new FaceCompareResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceCompareResponse resp = faceOps.compareFile(new File("/tmp/a.jpg"), new File("/tmp/b.jpg"));

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void searchUrl_callsDoPartRequest() throws IOException {
        FaceSearchResponse expected = new FaceSearchResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceSearchOptions options = FaceSearchOptions.builder().build();
        FaceSearchResponse resp = faceOps.searchUrl("http://img.url", options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void searchToken_callsDoPartRequest() throws IOException {
        FaceSearchResponse expected = new FaceSearchResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceSearchOptions options = FaceSearchOptions.builder().build();
        FaceSearchResponse resp = faceOps.searchToken("token1", options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void searchBase64_callsDoPartRequest() throws IOException {
        FaceSearchResponse expected = new FaceSearchResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceSearchOptions options = FaceSearchOptions.builder().build();
        FaceSearchResponse resp = faceOps.searchBase64("base64data", options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void searchFile_callsDoPartRequest() throws IOException {
        FaceSearchResponse expected = new FaceSearchResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        FaceSearchOptions options = FaceSearchOptions.builder().build();
        FaceSearchResponse resp = faceOps.searchFile(new File("/tmp/test.jpg"), options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void skinAnalyzeUrl_callsDoPartRequest() throws IOException {
        FaceSkinAnalyzeResponse expected = new FaceSkinAnalyzeResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        SkinAnalyzeOptions options = SkinAnalyzeOptions.builder().build();
        FaceSkinAnalyzeResponse resp = faceOps.skinAnalyzeUrl("http://img.url", SkinAnalyzeType.BASIC, options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void skinAnalyzeBase64_callsDoPartRequest() throws IOException {
        FaceSkinAnalyzeResponse expected = new FaceSkinAnalyzeResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        SkinAnalyzeOptions options = SkinAnalyzeOptions.builder().build();
        FaceSkinAnalyzeResponse resp = faceOps.skinAnalyzeBase64("base64data", SkinAnalyzeType.ADVANCED, options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void skinAnalyzeFile_callsDoPartRequest() throws IOException {
        FaceSkinAnalyzeResponse expected = new FaceSkinAnalyzeResponse();
        expected.setCode(200);
        when(mockTemplate.doPartRequest(anyString(), any(), any())).thenReturn(expected);

        SkinAnalyzeOptions options = SkinAnalyzeOptions.builder().build();
        FaceSkinAnalyzeResponse resp = faceOps.skinAnalyzeFile(new File("/tmp/test.jpg"), SkinAnalyzeType.PRO, options);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    // --- FaceppFacesetOperations tests ---

    @Test
    void createFaceset_callsPost() throws IOException {
        FacesetCreateResponse expected = new FacesetCreateResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetBo bo = new FacesetBo();
        bo.setDisplayName("Test");
        FacesetCreateResponse resp = facesetOps.createFaceset(bo);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void deleteFaceset_callsPost() throws IOException {
        FacesetCreateResponse expected = new FacesetCreateResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetDeleteBo bo = new FacesetDeleteBo();
        bo.setOuterId("outer1");
        FacesetCreateResponse resp = facesetOps.deleteFaceset(bo);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void updateFaceset_callsPost() throws IOException {
        FacesetCreateResponse expected = new FacesetCreateResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetUpdateBo bo = new FacesetUpdateBo();
        bo.setDisplayName("Updated");
        FacesetCreateResponse resp = facesetOps.updateFaceset(bo);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void getFacesetList_callsPost() throws IOException {
        FacesetListResponse expected = new FacesetListResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetListResponse resp = facesetOps.getFacesetList(1, "tag1", "tag2");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void getFacesetList_withSingleTag_callsPost() throws IOException {
        FacesetListResponse expected = new FacesetListResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetListResponse resp = facesetOps.getFacesetList(1, "tag1");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void getFacesetList_withNoTags_callsPost() throws IOException {
        FacesetListResponse expected = new FacesetListResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetListResponse resp = facesetOps.getFacesetList(1);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void getFacesetByToken_callsPost() throws IOException {
        FacesetDetailResponse expected = new FacesetDetailResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetDetailResponse resp = facesetOps.getFacesetByToken("token1", 1);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void getFacesetByOuterId_callsPost() throws IOException {
        FacesetDetailResponse expected = new FacesetDetailResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FacesetDetailResponse resp = facesetOps.getFacesetByOuterId("outer1", 1);

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void addFaceWithToken_callsPost() throws IOException {
        FaceAddResponse expected = new FaceAddResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceAddResponse resp = facesetOps.addFaceWithToken("token1", "face1", "face2");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void addFaceWithOuterId_callsPost() throws IOException {
        FaceAddResponse expected = new FaceAddResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceAddResponse resp = facesetOps.addFaceWithOuterId("outer1", "face1");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void removeFaceByToken_callsPost() throws IOException {
        FaceRemoveResponse expected = new FaceRemoveResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceRemoveResponse resp = facesetOps.removeFaceByToken("token1", "face1");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void removeFaceByOuterId_callsPost() throws IOException {
        FaceRemoveResponse expected = new FaceRemoveResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceRemoveResponse resp = facesetOps.removeFaceByOuterId("outer1", "face1");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void createFace_callsPost() throws IOException {
        FaceSetUserIdResponse expected = new FaceSetUserIdResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceSetUserIdResponse resp = facesetOps.createFace("faceToken1", "userId1");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void getFaceDetail_callsPost() throws IOException {
        FaceDetailResponse expected = new FaceDetailResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceDetailResponse resp = facesetOps.getFaceDetail("faceToken1");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    // --- FaceppFacesetAsyncOperations tests ---

    @Test
    void getFaceStatusByTaskId_callsPost() throws IOException {
        FaceStatusResponse expected = new FaceStatusResponse();
        expected.setCode(200);
        when(mockTemplate.post(anyString(), any(), any(), any())).thenReturn(expected);

        FaceStatusResponse resp = facesetAsyncOps.getFaceStatusByTaskId("task1");

        assertThat(resp.getCode()).isEqualTo(200);
    }

    // --- FaceppAutoConfiguration tests ---

    @Test
    void autoConfiguration_createsTemplates() {
        FaceppAutoConfiguration config = new FaceppAutoConfiguration();
        OkHttpClient client = new OkHttpClient.Builder().build();
        ObjectMapper mapper = new ObjectMapper();
        FaceppProperties props = new FaceppProperties();

        @SuppressWarnings("unchecked")
        org.springframework.beans.factory.ObjectProvider<OkHttpClient> clientProvider =
                Mockito.mock(org.springframework.beans.factory.ObjectProvider.class);
        when(clientProvider.getIfAvailable(Mockito.any())).thenReturn(client);

        @SuppressWarnings("unchecked")
        org.springframework.beans.factory.ObjectProvider<ObjectMapper> mapperProvider =
                Mockito.mock(org.springframework.beans.factory.ObjectProvider.class);
        when(mapperProvider.getIfAvailable(Mockito.any())).thenReturn(mapper);

        FaceppOkHttp3Template okhttp3Template = config.agoraOkHttp3Template(
                clientProvider, mapperProvider, props);
        FaceppTemplate template = config.agoraTemplate(okhttp3Template, props);

        assertThat(template).isNotNull();
        assertThat(template.getFaceppOkHttp3Template()).isNotNull();
        assertThat(template.getFaceppProperties()).isNotNull();
    }

    // --- FaceppOperations abstract class tests ---

    @Test
    void getFaceppProperties_returnsProperties() {
        FaceppTemplate template = new FaceppTemplate(mockTemplate, properties);
        assertThat(template.getFaceppProperties()).isEqualTo(properties);
    }

    @Test
    void getFaceppOkHttp3Template_returnsTemplate() {
        FaceppTemplate template = new FaceppTemplate(mockTemplate, properties);
        assertThat(template.getFaceppOkHttp3Template()).isEqualTo(mockTemplate);
    }

    // --- Async operations tests ---

    @Test
    void asyncAddFaceWithToken_callsDoAsyncRequest() throws IOException {
        Mockito.doNothing().when(mockTemplate).doAsyncRequest(
                anyString(), any(), any(), any());

        facesetAsyncOps.asyncAddFaceWithToken("token1", new String[]{"face1"}, resp -> {});
    }

    @Test
    void asyncAddFaceWithOuterId_callsDoAsyncRequest() throws IOException {
        Mockito.doNothing().when(mockTemplate).doAsyncRequest(
                anyString(), any(), any(), any());

        facesetAsyncOps.asyncAddFaceWithOuterId("outer1", new String[]{"face1"}, resp -> {});
    }

    @Test
    void asyncRemoveFaceByToken_callsDoAsyncRequest() throws IOException {
        Mockito.doNothing().when(mockTemplate).doAsyncRequest(
                anyString(), any(), any(), any());

        facesetAsyncOps.asyncRemoveFaceByToken("token1", new String[]{"face1"}, resp -> {});
    }

    @Test
    void asyncRemoveFaceByOuterId_callsDoAsyncRequest() throws IOException {
        Mockito.doNothing().when(mockTemplate).doAsyncRequest(
                anyString(), any(), any(), any());

        facesetAsyncOps.asyncRemoveFaceByOuterId("outer1", new String[]{"face1"}, resp -> {});
    }

    // --- Template getters tests ---

    @Test
    void opsForFaceset_returnsNonNull() {
        FaceppTemplate template = new FaceppTemplate(mockTemplate, properties);
        assertThat(template.opsForFaceset()).isNotNull();
    }

    @Test
    void opsForFaceDetect_returnsNonNull() {
        FaceppTemplate template = new FaceppTemplate(mockTemplate, properties);
        assertThat(template.opsForFaceDetect()).isNotNull();
    }
}

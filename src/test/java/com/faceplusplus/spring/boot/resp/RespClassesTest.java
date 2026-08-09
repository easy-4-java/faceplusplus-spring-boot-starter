package com.faceplusplus.spring.boot.resp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for response model classes.
 *
 * @author Loong Wan (https://github.com/loong10k)
 */
class RespClassesTest {

    @Test
    void faceppResponse_defaultValues() {
        FaceppResponse resp = new FaceppResponse();
        assertThat(resp.getCode()).isEqualTo(0);
        assertThat(resp.getTimeUsed()).isEqualTo(0);
        assertThat(resp.getRequestId()).isNull();
        assertThat(resp.getErrorMsg()).isNull();
        assertThat(resp.isSuccess()).isFalse();
    }

    @Test
    void faceppResponse_settersAndGetters() {
        FaceppResponse resp = new FaceppResponse();
        resp.setCode(200);
        resp.setTimeUsed(150);
        resp.setRequestId("req123");
        resp.setErrorMsg("error");

        assertThat(resp.getCode()).isEqualTo(200);
        assertThat(resp.getTimeUsed()).isEqualTo(150);
        assertThat(resp.getRequestId()).isEqualTo("req123");
        assertThat(resp.getErrorMsg()).isEqualTo("error");
        assertThat(resp.isSuccess()).isTrue();
    }

    @Test
    void faceppResponse_isSuccess_returnsFalseForNon200() {
        FaceppResponse resp = new FaceppResponse();
        resp.setCode(500);
        assertThat(resp.isSuccess()).isFalse();
    }

    @Test
    void faceDetectResponse_inheritsFromFaceppResponse() {
        FaceDetectResponse resp = new FaceDetectResponse();
        resp.setCode(200);
        resp.setRequestId("req1");
        assertThat(resp.getCode()).isEqualTo(200);
        assertThat(resp.getRequestId()).isEqualTo("req1");
    }

    @Test
    void faceAnalyzeResponse_inheritsFromFaceppResponse() {
        FaceAnalyzeResponse resp = new FaceAnalyzeResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceCompareResponse_inheritsFromFaceppResponse() {
        FaceCompareResponse resp = new FaceCompareResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceSearchResponse_inheritsFromFaceppResponse() {
        FaceSearchResponse resp = new FaceSearchResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceAddResponse_inheritsFromFaceppResponse() {
        FaceAddResponse resp = new FaceAddResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceRemoveResponse_inheritsFromFaceppResponse() {
        FaceRemoveResponse resp = new FaceRemoveResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceDetailResponse_inheritsFromFaceppResponse() {
        FaceDetailResponse resp = new FaceDetailResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceSetUserIdResponse_inheritsFromFaceppResponse() {
        FaceSetUserIdResponse resp = new FaceSetUserIdResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceStatusResponse_inheritsFromFaceppResponse() {
        FaceStatusResponse resp = new FaceStatusResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceSkinAnalyzeResponse_inheritsFromFaceppResponse() {
        FaceSkinAnalyzeResponse resp = new FaceSkinAnalyzeResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void facesetCreateResponse_inheritsFromFaceppResponse() {
        FacesetCreateResponse resp = new FacesetCreateResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void facesetDeleteResponse_inheritsFromFaceppResponse() {
        FacesetDeleteResponse resp = new FacesetDeleteResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void facesetDetailResponse_inheritsFromFaceppResponse() {
        FacesetDetailResponse resp = new FacesetDetailResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void facesetListResponse_inheritsFromFaceppResponse() {
        FacesetListResponse resp = new FacesetListResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void facesetUpdateResponse_inheritsFromFaceppResponse() {
        FacesetUpdateResponse resp = new FacesetUpdateResponse();
        resp.setCode(200);
        assertThat(resp.getCode()).isEqualTo(200);
    }

    @Test
    void faceRectangle_settersAndGetters() {
        FaceRectangle rect = new FaceRectangle();
        rect.setTop(10);
        rect.setLeft(20);
        rect.setWidth(100);
        rect.setHeight(200);

        assertThat(rect.getTop()).isEqualTo(10);
        assertThat(rect.getLeft()).isEqualTo(20);
        assertThat(rect.getWidth()).isEqualTo(100);
        assertThat(rect.getHeight()).isEqualTo(200);
    }

    @Test
    void faceLandmark_settersAndGetters() {
        FaceLandmark landmark = new FaceLandmark();
        landmark.setTop(10);
        landmark.setLeft(20);
        landmark.setWidth(100);
        landmark.setHeight(200);

        assertThat(landmark.getTop()).isEqualTo(10);
        assertThat(landmark.getLeft()).isEqualTo(20);
        assertThat(landmark.getWidth()).isEqualTo(100);
        assertThat(landmark.getHeight()).isEqualTo(200);
    }

    @Test
    void faceAttributes_settersAndGetters() {
        FaceAttributes attrs = new FaceAttributes();
        FaceAttributes.FaceAttrValue gender = new FaceAttributes.FaceAttrValue();
        gender.setValue("male");
        attrs.setGender(gender);

        FaceAttributes.FaceAttrValue age = new FaceAttributes.FaceAttrValue();
        age.setValue("25");
        attrs.setAge(age);

        assertThat(attrs.getGender().getValue()).isEqualTo("male");
        assertThat(attrs.getAge().getValue()).isEqualTo("25");
    }

    @Test
    void faceAttributes_smile_settersAndGetters() {
        FaceAttributes.FaceAttrSmile smile = new FaceAttributes.FaceAttrSmile();
        smile.setValue(80.5f);
        smile.setThreshold(50.0f);

        assertThat(smile.getValue()).isEqualTo(80.5f);
        assertThat(smile.getThreshold()).isEqualTo(50.0f);
    }

    @Test
    void faceAttributes_headpose_settersAndGetters() {
        FaceAttributes.FaceAttrHeadpose headpose = new FaceAttributes.FaceAttrHeadpose();
        headpose.setYaw(10.5f);
        headpose.setPitch(20.5f);
        headpose.setRoll(30.5f);

        assertThat(headpose.getYaw()).isEqualTo(10.5f);
        assertThat(headpose.getPitch()).isEqualTo(20.5f);
        assertThat(headpose.getRoll()).isEqualTo(30.5f);
    }

    @Test
    void faceAttributes_blur_settersAndGetters() {
        FaceAttributes.FaceAttrBlur blur = new FaceAttributes.FaceAttrBlur();
        blur.setYaw(10.5f);
        blur.setPitch(20.5f);
        blur.setRoll(30.5f);

        assertThat(blur.getYaw()).isEqualTo(10.5f);
        assertThat(blur.getPitch()).isEqualTo(20.5f);
        assertThat(blur.getRoll()).isEqualTo(30.5f);
    }

    @Test
    void faceSetFetail_settersAndGetters() {
        FaceSetFetail detail = new FaceSetFetail();
        detail.setFacesetToken("token1");
        detail.setOuterId("outer1");
        detail.setTags("tag1");

        assertThat(detail.getFacesetToken()).isEqualTo("token1");
        assertThat(detail.getOuterId()).isEqualTo("outer1");
        assertThat(detail.getTags()).isEqualTo("tag1");
    }
}

package com.faceplusplus.spring.boot.req;

import com.faceplusplus.spring.boot.FaceppApiAddress;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for request model classes.
 *
 * @author Loong Wan (https://github.com/loong10k)
 */
class ReqClassesTest {

    @Test
    void faceDetectOptions_builderWorks() {
        FaceDetectOptions options = FaceDetectOptions.builder()
                .returnLandmark(2)
                .returnAttributes("gender,age")
                .calculateAll(1)
                .faceRectangle("70,80,100,100")
                .beautyScoreMin(10)
                .beautyScoreMax(90)
                .build();

        assertThat(options.getReturnLandmark()).isEqualTo(2);
        assertThat(options.getReturnAttributes()).isEqualTo("gender,age");
        assertThat(options.getCalculateAll()).isEqualTo(1);
        assertThat(options.getFaceRectangle()).isEqualTo("70,80,100,100");
        assertThat(options.getBeautyScoreMin()).isEqualTo(10);
        assertThat(options.getBeautyScoreMax()).isEqualTo(90);
    }

    @Test
    void faceAnalyzeOptions_builderWorks() {
        FaceAnalyzeOptions options = FaceAnalyzeOptions.builder()
                .returnLandmark(2)
                .returnAttributes("gender,age")
                .beautyScoreMin(10)
                .beautyScoreMax(90)
                .build();

        assertThat(options.getReturnLandmark()).isEqualTo(2);
        assertThat(options.getReturnAttributes()).isEqualTo("gender,age");
        assertThat(options.getBeautyScoreMin()).isEqualTo(10);
        assertThat(options.getBeautyScoreMax()).isEqualTo(90);
    }

    @Test
    void faceSearchOptions_builderWorks() {
        FaceSearchOptions options = FaceSearchOptions.builder()
                .returnLandmark(2)
                .returnAttributes("gender,age")
                .beautyScoreMin(10)
                .beautyScoreMax(90)
                .build();

        assertThat(options.getReturnLandmark()).isEqualTo(2);
        assertThat(options.getReturnAttributes()).isEqualTo("gender,age");
        assertThat(options.getBeautyScoreMin()).isEqualTo(10);
        assertThat(options.getBeautyScoreMax()).isEqualTo(90);
    }

    @Test
    void skinAnalyzeOptions_builderWorks() {
        SkinAnalyzeOptions options = SkinAnalyzeOptions.builder()
                .faceQualityControl(1)
                .returnRectConfidence(1)
                .returnMaps("red_area, brown_area")
                .build();

        assertThat(options.getFaceQualityControl()).isEqualTo(1);
        assertThat(options.getReturnRectConfidence()).isEqualTo(1);
        assertThat(options.getReturnMaps()).isEqualTo("red_area, brown_area");
    }

    @Test
    void skinAnalyzeType_basic_returnsCorrectApiAddress() {
        assertThat(SkinAnalyzeType.BASIC.getApiAddress()).isEqualTo(FaceppApiAddress.FACE_SKIN_ANALYZE);
    }

    @Test
    void skinAnalyzeType_advanced_returnsCorrectApiAddress() {
        assertThat(SkinAnalyzeType.ADVANCED.getApiAddress()).isEqualTo(FaceppApiAddress.FACE_SKIN_ANALYZE_ADVANCED);
    }

    @Test
    void skinAnalyzeType_pro_returnsCorrectApiAddress() {
        assertThat(SkinAnalyzeType.PRO.getApiAddress()).isEqualTo(FaceppApiAddress.FACE_SKIN_ANALYZE_PRO);
    }

    @Test
    void skinAnalyzeType_allValues_arePresent() {
        assertThat(SkinAnalyzeType.values()).hasSize(3);
    }

    @Test
    void facesetBo_settersWork() {
        FacesetBo bo = new FacesetBo();
        bo.setDisplayName("Test FaceSet");
        bo.setOuterId("outer1");
        bo.setTags("tag1,tag2");
        bo.setFaceTokens("token1,token2");
        bo.setUserData("data");
        bo.setForceMerge(1);

        assertThat(bo.getDisplayName()).isEqualTo("Test FaceSet");
        assertThat(bo.getOuterId()).isEqualTo("outer1");
        assertThat(bo.getTags()).isEqualTo("tag1,tag2");
        assertThat(bo.getFaceTokens()).isEqualTo("token1,token2");
        assertThat(bo.getUserData()).isEqualTo("data");
        assertThat(bo.getForceMerge()).isEqualTo(1);
    }

    @Test
    void facesetDeleteBo_settersWork() {
        FacesetDeleteBo bo = new FacesetDeleteBo();
        bo.setOuterId("outer");
        bo.setFaceTokens("tokens");
        bo.setCheckEmpty(0);

        assertThat(bo.getOuterId()).isEqualTo("outer");
        assertThat(bo.getFaceTokens()).isEqualTo("tokens");
        assertThat(bo.getCheckEmpty()).isEqualTo(0);
    }

    @Test
    void facesetUpdateBo_settersWork() {
        FacesetUpdateBo bo = new FacesetUpdateBo();
        bo.setFaceToken("token");
        bo.setOuterId("outer");
        bo.setDisplayName("name");
        bo.setTags("tags");
        bo.setNewOuterId("newOuter");
        bo.setUserData("data");

        assertThat(bo.getFaceToken()).isEqualTo("token");
        assertThat(bo.getOuterId()).isEqualTo("outer");
        assertThat(bo.getDisplayName()).isEqualTo("name");
        assertThat(bo.getTags()).isEqualTo("tags");
        assertThat(bo.getNewOuterId()).isEqualTo("newOuter");
        assertThat(bo.getUserData()).isEqualTo("data");
    }
}

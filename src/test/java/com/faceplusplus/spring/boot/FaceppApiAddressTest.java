package com.faceplusplus.spring.boot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link FaceppApiAddress}.
 *
 * @author Loong Wan (https://github.com/loong10k)
 */
class FaceppApiAddressTest {

    @Test
    void faceSetCreate_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACESET_CREATE.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/faceset/create");
        assertThat(FaceppApiAddress.FACESET_CREATE.getMethod()).isEqualTo(RequestMethod.POST);
        assertThat(FaceppApiAddress.FACESET_CREATE.getOpt()).isEqualTo("Create a FaceSet.");
    }

    @Test
    void faceSetDelete_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACESET_DELETE.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/faceset/delete");
    }

    @Test
    void faceSetUpdate_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACESET_UPDATE.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/faceset/update");
    }

    @Test
    void faceSetList_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACESET_LIST.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/faceset/getfacesets");
    }

    @Test
    void faceSetDetail_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACESET_DETAIL.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail");
    }

    @Test
    void faceAdd_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_ADD.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/faceset/addface");
    }

    @Test
    void faceRemove_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_REMOVE.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface");
    }

    @Test
    void faceDetect_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_DETECT.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/detect");
    }

    @Test
    void faceAnalyze_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_ANALYZE.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/face/analyze");
    }

    @Test
    void faceCompare_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_COMPARE.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/compare");
    }

    @Test
    void faceSearch_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_SEARCH.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/search");
    }

    @Test
    void faceSetUserId_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_SET_USERID.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/face/setuserid");
    }

    @Test
    void faceGetDetail_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_GET_DETAIL.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v3/face/getdetail");
    }

    @Test
    void faceSkinAnalyze_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_SKIN_ANALYZE.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v1/skinanalyze");
    }

    @Test
    void faceSkinAnalyzeAdvanced_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_SKIN_ANALYZE_ADVANCED.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v1/skinanalyze_advanced");
    }

    @Test
    void faceSkinAnalyzePro_hasCorrectUrl() {
        assertThat(FaceppApiAddress.FACE_SKIN_ANALYZE_PRO.getUrl())
                .isEqualTo("https://api-cn.faceplusplus.com/facepp/v1/skinanalyze_pro");
    }

    @Test
    void getUrlWithArgs_formatsUrl() {
        String url = FaceppApiAddress.ACQUIRE_RESOURCE_ID.getUrl();
        assertThat(url).isEqualTo("/facepp/v3/detect");
    }

    @Test
    void allEnumValues_areAccessible() {
        FaceppApiAddress[] values = FaceppApiAddress.values();
        assertThat(values.length).isGreaterThan(0);
    }

    @Test
    void enumValueOf_worksCorrectly() {
        assertThat(FaceppApiAddress.valueOf("FACE_DETECT")).isEqualTo(FaceppApiAddress.FACE_DETECT);
    }
}

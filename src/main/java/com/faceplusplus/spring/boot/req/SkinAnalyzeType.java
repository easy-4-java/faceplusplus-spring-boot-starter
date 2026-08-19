package com.faceplusplus.spring.boot.req;

import com.faceplusplus.spring.boot.FaceppApiAddress;

import java.util.function.Function;

/**
 * Model class for SkinAnalyzeType.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public enum SkinAnalyzeType {

    BASIC((x) -> {
        return FaceppApiAddress.FACE_SKIN_ANALYZE;
    }),
    ADVANCED((x) -> {
        return FaceppApiAddress.FACE_SKIN_ANALYZE_ADVANCED;
    }),
    PRO((x) -> {
        return FaceppApiAddress.FACE_SKIN_ANALYZE_PRO;
    });

    Function<Object, FaceppApiAddress> function;

    SkinAnalyzeType(Function<Object, FaceppApiAddress> function){
        this.function = function;
    }

    /** @return return the api address. */
    public FaceppApiAddress getApiAddress(){
        return function.apply(this);
    }


}

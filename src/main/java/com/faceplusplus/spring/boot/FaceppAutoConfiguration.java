package com.faceplusplus.spring.boot;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import okhttp3.OkHttpClient;

@Configuration
@EnableConfigurationProperties({ FaceppProperties.class})
public class FaceppAutoConfiguration {

	@Bean
	public FaceppOkHttp3Template agoraOkHttp3Template(ObjectProvider<OkHttpClient> okhttp3ClientProvider,
													  ObjectProvider<ObjectMapper> objectMapperProvider,
													  FaceppProperties poolProperties) {

		OkHttpClient okhttp3Client = okhttp3ClientProvider.getIfAvailable(() -> new OkHttpClient.Builder().build());

		ObjectMapper objectMapper = objectMapperProvider.getIfAvailable(() -> {
			ObjectMapper objectMapperDef = new ObjectMapper();
			objectMapperDef.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			objectMapperDef.enable(MapperFeature.USE_GETTERS_AS_SETTERS);
			objectMapperDef.enable(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
			objectMapperDef.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			objectMapperDef.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return objectMapperDef;
		});

		return new FaceppOkHttp3Template(okhttp3Client, objectMapper, poolProperties);
	}

	@Bean
	public FaceppTemplate agoraTemplate(FaceppOkHttp3Template faceppOkHttp3Template,
										FaceppProperties poolProperties) {
		return new FaceppTemplate(faceppOkHttp3Template, poolProperties );
	}

}

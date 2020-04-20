package com.example.boot.core.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间序列号
 * @author panzhi
 *
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
		generator.writeString(value.format(formatter));
	}

}

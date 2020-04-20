package com.example.boot.core.converter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 日期序列号
 * @author panzhi
 *
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate>{

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
		generator.writeString(value.format(formatter));
	}

}

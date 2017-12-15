package com.contguard.telemetry.server.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DateConverter implements AttributeConverter<LocalDateTime, Date> {
    public DateConverter() {
    }

    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    public LocalDateTime convertToEntityAttribute(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
    }
}
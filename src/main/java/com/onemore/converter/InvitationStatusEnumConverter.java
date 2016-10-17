package com.onemore.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.onemore.model.InvitationStatusEnum;

@Converter
public class InvitationStatusEnumConverter implements AttributeConverter<InvitationStatusEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(InvitationStatusEnum value) {
		if ( value == null ) {
			return null;
		}

		return value.getCode();
	}

	@Override
	public InvitationStatusEnum convertToEntityAttribute(Integer value) {
		if ( value == null ) {
			return null;
		}

		return InvitationStatusEnum.fromCode( value );
	}

}
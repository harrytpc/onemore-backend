package com.onemore.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.onemore.model.InvitationTypeEnum;

@Converter
public class InvitationTypeEnumConverter implements AttributeConverter<InvitationTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(InvitationTypeEnum value) {
		if ( value == null ) {
			return null;
		}

		return value.getCode();
	}

	@Override
	public InvitationTypeEnum convertToEntityAttribute(Integer value) {
		if ( value == null ) {
			return null;
		}

		return InvitationTypeEnum.fromCode( value );
	}

}
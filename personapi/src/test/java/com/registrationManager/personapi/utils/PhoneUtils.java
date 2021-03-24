package com.registrationManager.personapi.utils;

import com.registrationManager.personapi.dto.request.PhoneDTO;
import com.registrationManager.personapi.entity.Phone;
import com.registrationManager.personapi.enums.PhoneType;

public class PhoneUtils {

    //entidades fakes de Phone
    private static final String PHONE_NUMBER = "1199999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    //Phone DTO
    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    //Phone Entity
    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}

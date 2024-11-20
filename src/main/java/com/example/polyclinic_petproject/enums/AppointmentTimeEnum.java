package com.example.polyclinic_petproject.enums;

import java.util.Random;

public enum AppointmentTimeEnum {
    time_1("09:00"),
    time_2("10:00"),
    time_3("11:00"),
    time_4("12:00"),
    time_5("13:00"),
    time_6("14:00"),
    time_7("15:00"),
    time_8("16:00"),
    time_9("17:00");

    private final String displayName;

    AppointmentTimeEnum(String displayName) {
        this.displayName = displayName;
    }

    public static AppointmentTimeEnum returnRandomTime() {
        int pic = new Random().nextInt(AppointmentTimeEnum.values().length);
        return AppointmentTimeEnum.values()[pic];
    }


    public String getDisplayName() {
        return displayName;
    }
    public static AppointmentTimeEnum fromDisplayName(String displayName) {
        for (AppointmentTimeEnum timeEnum : AppointmentTimeEnum.values()) {
            if (timeEnum.getDisplayName().equals(displayName)) {
                return timeEnum;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + displayName);
    }
}

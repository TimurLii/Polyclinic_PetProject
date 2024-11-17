package com.example.polyclinic_petproject.enums;

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

    public String getDisplayName() {
        return displayName;
    }
}

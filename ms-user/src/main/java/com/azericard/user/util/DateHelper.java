package com.azericard.user.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    public static Date getCurrentTime() {
        return Date.from(Instant.now());
    }

    public static Date calculateTokenExpireDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.HOUR, 3);
        return new Date(cal.getTime().getTime());
    }
}

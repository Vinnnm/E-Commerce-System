package com.vinnnm.ecommercesystem.util;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Helper {
    public static long getCurrentTimestamp() {
        Date createdAt = new Date();
        return createdAt.getTime();
    }
    public static Date convertTimestampToDate(long timestamp) {
        return new Date(timestamp);
    }
}

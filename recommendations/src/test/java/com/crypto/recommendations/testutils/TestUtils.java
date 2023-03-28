package com.crypto.recommendations.testutils;

import com.crypto.recommendations.model.Crypto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class TestUtils {

    public static Crypto createCrypto(Long timestamp, String name, double cost) {
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                TimeZone.getDefault().toZoneId());

        BigDecimal price = new BigDecimal(cost);

        return new Crypto(name, date, price);
    }

}

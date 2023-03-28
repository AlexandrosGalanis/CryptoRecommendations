package com.crypto.recommendations.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Crypto {

    private final String name;
    private final LocalDateTime timestamp;
    private final BigDecimal price;

    public Crypto(String name, LocalDateTime timestamp, BigDecimal price) {
        this.name = name;
        this.timestamp = timestamp;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Crypto{" + "typeOfCrypto='" + name + '\'' + ", timestamp=" + timestamp + ", price=" + price + "}";
    }
}

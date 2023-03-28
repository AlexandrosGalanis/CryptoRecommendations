package com.crypto.recommendations.dto;


import java.math.BigDecimal;

public class CryptoNormalizedRangeDTO {

    private final String cryptoName;
    private final BigDecimal normalizedRange;

    public CryptoNormalizedRangeDTO(String cryptoName, BigDecimal normalizedRange) {
        this.cryptoName = cryptoName;
        this.normalizedRange = normalizedRange;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public BigDecimal getNormalizedRange() {
        return normalizedRange;
    }

    @Override
    public String toString() {
        return "NormalizedRangeCrypto{" +
                "cryptoName='" + cryptoName + '\'' +
                ", normalizedRange=" + normalizedRange +
                "}\n";
    }
}

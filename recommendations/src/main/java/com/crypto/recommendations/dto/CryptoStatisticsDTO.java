package com.crypto.recommendations.dto;

import com.crypto.recommendations.model.Crypto;

public class CryptoStatisticsDTO {

    private final Crypto oldest;
    private final Crypto newest;
    private final Crypto minimumValue;
    private final Crypto maximumValue;

    public CryptoStatisticsDTO(CryptoStatisticsDTOBuilder cryptoStatisticsDTOBuilder) {
        this.oldest = cryptoStatisticsDTOBuilder.oldest;
        this.newest = cryptoStatisticsDTOBuilder.newest;
        this.minimumValue = cryptoStatisticsDTOBuilder.minimumValue;
        this.maximumValue = cryptoStatisticsDTOBuilder.maximumValue;
    }

    public Crypto getOldest() {
        return oldest;
    }

    public Crypto getNewest() {
        return newest;
    }

    public Crypto getMinimumValue() {
        return minimumValue;
    }

    public Crypto getMaximumValue() {
        return maximumValue;
    }

    public static class CryptoStatisticsDTOBuilder {
        private Crypto oldest;
        private Crypto newest;
        private Crypto minimumValue;
        private Crypto maximumValue;

        public CryptoStatisticsDTOBuilder oldest(Crypto oldest) {
            this.oldest = oldest;
            return this;
        }

        public CryptoStatisticsDTOBuilder newest(Crypto newest) {
            this.newest = newest;
            return this;
        }

        public CryptoStatisticsDTOBuilder minimumValue(Crypto minimumValue) {
            this.minimumValue = minimumValue;
            return this;
        }

        public CryptoStatisticsDTOBuilder maximumValue(Crypto maximumValue) {
            this.maximumValue = maximumValue;
            return this;
        }

        public CryptoStatisticsDTO build() {
            return new CryptoStatisticsDTO(this);
        }
    }
}

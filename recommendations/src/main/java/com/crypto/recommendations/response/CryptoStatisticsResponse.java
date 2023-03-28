package com.crypto.recommendations.response;

import com.crypto.recommendations.dto.CryptoStatisticsDTO;

import java.util.List;

public class CryptoStatisticsResponse {
    private List<CryptoStatisticsDTO> cryptoStatisticsDTOs;
    private String errorMessage;

    public List<CryptoStatisticsDTO> getCryptoStatisticsDTOs() {
        return cryptoStatisticsDTOs;
    }

    public void setCryptoStatisticsDTOs(List<CryptoStatisticsDTO> cryptoStatisticsDTOs) {
        this.cryptoStatisticsDTOs = cryptoStatisticsDTOs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

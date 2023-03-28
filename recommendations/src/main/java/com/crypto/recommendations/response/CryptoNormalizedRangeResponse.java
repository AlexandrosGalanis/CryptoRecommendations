package com.crypto.recommendations.response;

import com.crypto.recommendations.dto.CryptoNormalizedRangeDTO;

import java.util.List;

public class CryptoNormalizedRangeResponse {

    private List<CryptoNormalizedRangeDTO> cryptoNormalizedRangeDTOS;
    private String errorMessage;

    public List<CryptoNormalizedRangeDTO> getNormalizedRangeCryptos() {
        return cryptoNormalizedRangeDTOS;
    }

    public void setNormalizedRangeCryptos(List<CryptoNormalizedRangeDTO> cryptoNormalizedRangeDTOS) {
        this.cryptoNormalizedRangeDTOS = cryptoNormalizedRangeDTOS;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

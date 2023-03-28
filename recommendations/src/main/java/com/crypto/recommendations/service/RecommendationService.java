package com.crypto.recommendations.service;

import com.crypto.recommendations.dto.CryptoNormalizedRangeDTO;
import com.crypto.recommendations.model.Crypto;
import com.crypto.recommendations.data.CryptoDataStorage;
import com.crypto.recommendations.dto.CryptoStatisticsDTO;
import com.crypto.recommendations.response.CryptoNormalizedRangeResponse;
import com.crypto.recommendations.response.CryptoStatisticsResponse;
import com.crypto.recommendations.utils.CalculationsUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    @Autowired
    private CryptoDataStorage cryptoDataStorage;

    public CryptoStatisticsResponse getStatistics() {
        List<CryptoStatisticsDTO> cryptoStatisticsDTOs = new ArrayList<>();
        Map<String, List<Crypto>> cryptoMap = cryptoDataStorage.getCryptoMap();

        for (String cryptoName : cryptoMap.keySet()) {
            if (CollectionUtils.isEmpty(cryptoDataStorage.getCryptoMap().get(cryptoName))) {
                continue;
            }
            CryptoStatisticsDTO cryptoStatisticsDTO = calculateCryptoStatisticsDTO(cryptoName);
            cryptoStatisticsDTOs.add(cryptoStatisticsDTO);
        }

        CryptoStatisticsResponse response = new CryptoStatisticsResponse();
        response.setCryptoStatisticsDTOs(cryptoStatisticsDTOs);

        return response;
    }

    public CryptoStatisticsResponse getStatistics(String cryptoName) {
        List<CryptoStatisticsDTO> cryptoStatisticsDTOs = new ArrayList<>();
        CryptoStatisticsResponse response = new CryptoStatisticsResponse();

        if (CollectionUtils.isEmpty(cryptoDataStorage.getCryptoMap().get(cryptoName))) {
            response.setErrorMessage("Crypto: " + cryptoName + " is not supported yet!");
        } else {
            CryptoStatisticsDTO cryptoStatisticsDTO = calculateCryptoStatisticsDTO(cryptoName);
            cryptoStatisticsDTOs.add(cryptoStatisticsDTO);
        }

        response.setCryptoStatisticsDTOs(cryptoStatisticsDTOs);
        return response;
    }

    private CryptoStatisticsDTO calculateCryptoStatisticsDTO(String cryptoName) {
        List<Crypto> cryptoList = cryptoDataStorage.getCryptoMap().get(cryptoName);

        return new CryptoStatisticsDTO.CryptoStatisticsDTOBuilder()
                .maximumValue(CalculationsUtil.getMaxPriceCrypto(cryptoList))
                .minimumValue(CalculationsUtil.getMinPriceCrypto(cryptoList))
                .oldest(CalculationsUtil.getOldestCrypto(cryptoList))
                .newest(CalculationsUtil.getNewestCrypto(cryptoList))
                .build();
    }

    public CryptoNormalizedRangeResponse normalizedRange() {
        CryptoNormalizedRangeResponse response = new CryptoNormalizedRangeResponse();
        response.setNormalizedRangeCryptos(CalculationsUtil.getSortedByNormalizedRange(cryptoDataStorage.getCryptoMap()));

        if(CollectionUtils.isEmpty(response.getNormalizedRangeCryptos())){
            response.setErrorMessage("There are no data");
        }

        return response;
    }

    public CryptoNormalizedRangeResponse normalizedRange(LocalDate localDate) {
        CryptoNormalizedRangeResponse response = new CryptoNormalizedRangeResponse();
        List<CryptoNormalizedRangeDTO> list = new ArrayList<>();

        list.add(CalculationsUtil.getHighestNormalizedRangeCryptoOnSpecificDay(cryptoDataStorage.getCryptoMap(), localDate));
        response.setNormalizedRangeCryptos(list);

        if(CollectionUtils.isEmpty(response.getNormalizedRangeCryptos())){
            response.setErrorMessage("There are no data");
        }

        return response;
    }
}

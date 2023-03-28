package com.crypto.recommendations.utils;

import com.crypto.recommendations.model.Crypto;
import com.crypto.recommendations.dto.CryptoNormalizedRangeDTO;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CalculationsUtil {

    public static Crypto getOldestCrypto(List<Crypto> cryptoList) {
        return cryptoList.stream()
                .min(Comparator.comparing(Crypto::getTimestamp))
                .orElse(null);
    }

    public static Crypto getNewestCrypto(List<Crypto> cryptoList) {
        return cryptoList.stream()
                .max(Comparator.comparing(Crypto::getTimestamp))
                .orElse(null);
    }

    public static Crypto getMinPriceCrypto(List<Crypto> cryptoList) {
        return cryptoList.stream()
                .min(Comparator.comparing(Crypto::getPrice))
                .orElse(null);
    }

    public static Crypto getMaxPriceCrypto(List<Crypto> cryptoList) {
        return cryptoList.stream()
                .max(Comparator.comparing(Crypto::getPrice))
                .orElse(null);
    }

    public static List<CryptoNormalizedRangeDTO> getSortedByNormalizedRange(Map<String, List<Crypto>> cryptoMap) {
        List<CryptoNormalizedRangeDTO> sortedList = new ArrayList<>();

        for (String cryptoName : cryptoMap.keySet()) {
            if (CollectionUtils.isNotEmpty(cryptoMap.get(cryptoName))) {
                BigDecimal max = getMaxPriceCrypto(cryptoMap.get(cryptoName)).getPrice();
                BigDecimal min = getMinPriceCrypto(cryptoMap.get(cryptoName)).getPrice();

                sortedList.add(new CryptoNormalizedRangeDTO(cryptoName, max.subtract(min).divide(min, 10, RoundingMode.FLOOR)));
            }
        }

        sortedList = sortedList.stream()
                .sorted(Comparator.comparing(CryptoNormalizedRangeDTO::getNormalizedRange, Collections.reverseOrder()))
                .collect(Collectors.toList());

        return sortedList;
    }

    public static CryptoNormalizedRangeDTO getHighestNormalizedRangeCryptoOnSpecificDay(
            Map<String, List<Crypto>> cryptoMap, LocalDate selectedDay) {

        List<CryptoNormalizedRangeDTO> normilizedRangeCryptoList = new ArrayList<>();

        for (String cryptoName : cryptoMap.keySet()) {
            List<Crypto> List = cryptoMap.get(cryptoName).stream()
                    .filter(crypto -> isSameDay(crypto.getTimestamp(), selectedDay))
                    .collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(List)) {
                BigDecimal max = getMaxPriceCrypto(List).getPrice();
                BigDecimal min = getMinPriceCrypto(List).getPrice();

                normilizedRangeCryptoList.add(new CryptoNormalizedRangeDTO(cryptoName, max.subtract(min).divide(min, 10, RoundingMode.FLOOR)));
            }
        }


        return normilizedRangeCryptoList.stream()
                .sorted(Comparator.comparing(CryptoNormalizedRangeDTO::getNormalizedRange, Collections.reverseOrder()))
                .max(Comparator.comparing(CryptoNormalizedRangeDTO::getNormalizedRange)).orElse(null);
    }

    private static boolean isSameDay(LocalDateTime firstDate, LocalDate secondDate) {
        return firstDate.getYear() == secondDate.getYear() &&
                firstDate.getMonthValue() == secondDate.getMonthValue() &&
                firstDate.getDayOfMonth() == secondDate.getDayOfMonth();
    }
}

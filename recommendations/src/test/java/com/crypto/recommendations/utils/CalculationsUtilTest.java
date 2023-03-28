package com.crypto.recommendations.utils;

import com.crypto.recommendations.dto.CryptoNormalizedRangeDTO;
import com.crypto.recommendations.model.Crypto;
import com.crypto.recommendations.testutils.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CalculationsUtilTest {

    @Test
    void oldestCryptoWhenTheListIsEmpty() {
        Crypto crypto = CalculationsUtil.getOldestCrypto(new ArrayList<>());
        assertNull(crypto);
    }

    @Test
    void oldestCrypto() {
        List<Crypto> cryptoList = new ArrayList<>();

        Crypto oldestCrypto = TestUtils.createCrypto(1641009600000L, "BTC", 46813.21);
        cryptoList.add(oldestCrypto);
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoList.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));

        Crypto crypto = CalculationsUtil.getOldestCrypto(cryptoList);

        assertEquals(oldestCrypto.getTimestamp(), crypto.getTimestamp());
    }

    @Test
    void newestCrypto() {
        List<Crypto> cryptoList = new ArrayList<>();

        Crypto newest = TestUtils.createCrypto(1641034800000L, "BTC", 46871.09);
        cryptoList.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoList.add(newest);

        Crypto crypto = CalculationsUtil.getNewestCrypto(cryptoList);

        assertEquals(newest.getTimestamp(), crypto.getTimestamp());
    }

    @Test
    void minPriceCrypto() {
        List<Crypto> cryptoList = new ArrayList<>();

        Crypto minPrice = TestUtils.createCrypto(1641009600000L, "BTC", 46813.21);
        cryptoList.add(minPrice);
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoList.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));

        Crypto crypto = CalculationsUtil.getMinPriceCrypto(cryptoList);

        assertEquals(minPrice.getPrice(), crypto.getPrice());
    }

    @Test
    void maxPriceCrypto() {
        List<Crypto> cryptoList = new ArrayList<>();

        Crypto maxPrice = TestUtils.createCrypto(1641031200000L, "BTC", 47143.98);
        cryptoList.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(maxPrice);
        cryptoList.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));

        Crypto crypto = CalculationsUtil.getMaxPriceCrypto(cryptoList);

        assertEquals(maxPrice.getPrice(), crypto.getPrice());
    }

    @Test
    void sortedByNormalizedRange() {
        Map<String, List<Crypto>> cryptoMap = new HashMap<>();
        List<Crypto> cryptoListBTC = new ArrayList<>();

        cryptoListBTC.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoListBTC.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoListBTC.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoListBTC.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));
        cryptoMap.put("BTC", cryptoListBTC);

        List<Crypto> cryptoListLTC = new ArrayList<>();
        cryptoListLTC.add(TestUtils.createCrypto(1641009600000L, "LTC", 40813.21));
        cryptoListLTC.add(TestUtils.createCrypto(1641020400000L, "LTC", 46979.61));
        cryptoListLTC.add(TestUtils.createCrypto(1641031200000L, "LTC", 47143.98));
        cryptoListLTC.add(TestUtils.createCrypto(1641034800000L, "LTC", 46871.09));
        cryptoMap.put("LTC", cryptoListLTC);

        List<CryptoNormalizedRangeDTO> result = CalculationsUtil.getSortedByNormalizedRange(cryptoMap);
        assertEquals(2, result.size());
        assertEquals("LTC", result.get(0).getCryptoName());
        assertEquals("BTC", result.get(1).getCryptoName());
        assertEquals("0.1551157088", result.get(0).getNormalizedRange().toString());
        assertEquals("0.0070657406", result.get(1).getNormalizedRange().toString());
    }

    @Test
    void highestNormalizedRangeCryptoOnSpecificDay() {
        Map<String, List<Crypto>> cryptoMap = new HashMap<>();
        List<Crypto> cryptoListBTC = new ArrayList<>();

        cryptoListBTC.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoListBTC.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoListBTC.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoListBTC.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));
        cryptoMap.put("BTC", cryptoListBTC);

        List<Crypto> cryptoListLTC = new ArrayList<>();
        cryptoListLTC.add(TestUtils.createCrypto(1641009600000L, "LTC", 40813.21));
        cryptoListLTC.add(TestUtils.createCrypto(1641020400000L, "LTC", 46979.61));
        cryptoListLTC.add(TestUtils.createCrypto(1641031200000L, "LTC", 47143.98));
        cryptoListLTC.add(TestUtils.createCrypto(1641034800000L, "LTC", 46871.09));
        cryptoMap.put("LTC", cryptoListLTC);

        CryptoNormalizedRangeDTO result =
                CalculationsUtil.getHighestNormalizedRangeCryptoOnSpecificDay(cryptoMap,
                        LocalDate.parse("2022-01-01"));

        assertEquals("LTC", result.getCryptoName());
        assertEquals("0.1551157088", result.getNormalizedRange().toString());
    }

    @Test
    void highestNormalizedRangeCryptoOnSpecificDayEmptyMap() {
        Map<String, List<Crypto>> cryptoMap = new HashMap<>();

        CryptoNormalizedRangeDTO result =
                CalculationsUtil.getHighestNormalizedRangeCryptoOnSpecificDay(cryptoMap,
                        LocalDate.parse("2022-01-01"));

        assertNull(result);
    }
}
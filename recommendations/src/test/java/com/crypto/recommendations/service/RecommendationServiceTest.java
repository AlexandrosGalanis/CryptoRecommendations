package com.crypto.recommendations.service;

import com.crypto.recommendations.data.CryptoDataStorage;
import com.crypto.recommendations.dto.CryptoNormalizedRangeDTO;
import com.crypto.recommendations.dto.CryptoStatisticsDTO;
import com.crypto.recommendations.model.Crypto;
import com.crypto.recommendations.response.CryptoNormalizedRangeResponse;
import com.crypto.recommendations.testutils.TestUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RecommendationServiceTest {

    @Mock
    private CryptoDataStorage cryptoDataStorage;

    @InjectMocks
    private RecommendationService recommendationService;

    @Test
    @Before
    public void getStatistics() {
        MockitoAnnotations.openMocks(this);

        Map<String, List<Crypto>> cryptoMap = new HashMap<>();
        List<Crypto> cryptoList = new ArrayList<>();

        cryptoList.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoList.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));

        cryptoMap.put("BTC", cryptoList);
        when(cryptoDataStorage.getCryptoMap()).thenReturn(cryptoMap);

        CryptoStatisticsDTO expected = new CryptoStatisticsDTO.CryptoStatisticsDTOBuilder()
                .maximumValue(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98))
                .minimumValue(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21))
                .oldest(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21))
                .newest(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09))
                .build();

        assertEquals(expected.getMaximumValue().getPrice(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getMaximumValue().getPrice());
        assertEquals(expected.getMaximumValue().getTimestamp(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getMaximumValue().getTimestamp());
        assertEquals(expected.getMaximumValue().getName(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getMaximumValue().getName());

        assertEquals(expected.getMinimumValue().getPrice(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getMinimumValue().getPrice());
        assertEquals(expected.getMinimumValue().getTimestamp(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getMinimumValue().getTimestamp());
        assertEquals(expected.getMinimumValue().getName(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getMinimumValue().getName());

        assertEquals(expected.getOldest().getPrice(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getOldest().getPrice());
        assertEquals(expected.getOldest().getTimestamp(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getOldest().getTimestamp());
        assertEquals(expected.getOldest().getName(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getOldest().getName());

        assertEquals(expected.getNewest().getPrice(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getNewest().getPrice());
        assertEquals(expected.getNewest().getTimestamp(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getNewest().getTimestamp());
        assertEquals(expected.getNewest().getName(),
                recommendationService.getStatistics().getCryptoStatisticsDTOs().get(0).getNewest().getName());
    }

    @Test
    @Before
    public void testGetStatistics() {
        MockitoAnnotations.openMocks(this);

        Map<String, List<Crypto>> cryptoMap = new HashMap<>();
        List<Crypto> cryptoList = new ArrayList<>();

        cryptoList.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoList.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));

        cryptoMap.put("BTC", cryptoList);
        when(cryptoDataStorage.getCryptoMap()).thenReturn(cryptoMap);

        CryptoStatisticsDTO expected = new CryptoStatisticsDTO.CryptoStatisticsDTOBuilder()
                .maximumValue(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98))
                .minimumValue(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21))
                .oldest(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21))
                .newest(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09))
                .build();

        assertEquals(expected.getMaximumValue().getPrice(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getMaximumValue().getPrice());
        assertEquals(expected.getMaximumValue().getTimestamp(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getMaximumValue().getTimestamp());
        assertEquals(expected.getMaximumValue().getName(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getMaximumValue().getName());

        assertEquals(expected.getMinimumValue().getPrice(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getMinimumValue().getPrice());
        assertEquals(expected.getMinimumValue().getTimestamp(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getMinimumValue().getTimestamp());
        assertEquals(expected.getMinimumValue().getName(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getMinimumValue().getName());

        assertEquals(expected.getOldest().getPrice(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getOldest().getPrice());
        assertEquals(expected.getOldest().getTimestamp(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getOldest().getTimestamp());
        assertEquals(expected.getOldest().getName(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getOldest().getName());

        assertEquals(expected.getNewest().getPrice(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getNewest().getPrice());
        assertEquals(expected.getNewest().getTimestamp(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getNewest().getTimestamp());
        assertEquals(expected.getNewest().getName(),
                recommendationService.getStatistics("BTC").getCryptoStatisticsDTOs().get(0).getNewest().getName());
    }

    @Test
    @Before
    public void normalizedRange() {
        MockitoAnnotations.openMocks(this);

        Map<String, List<Crypto>> cryptoMap = new HashMap<>();
        List<Crypto> cryptoList = new ArrayList<>();

        cryptoList.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoList.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));

        cryptoMap.put("BTC", cryptoList);
        when(cryptoDataStorage.getCryptoMap()).thenReturn(cryptoMap);

        CryptoNormalizedRangeResponse expected = new CryptoNormalizedRangeResponse();
        List<CryptoNormalizedRangeDTO> cryptoNormalizedRangeDTOList = new ArrayList<>();
        cryptoNormalizedRangeDTOList.add(new CryptoNormalizedRangeDTO("BTC", BigDecimal.valueOf(0.0070657406)));
        expected.setNormalizedRangeCryptos(cryptoNormalizedRangeDTOList);

        assertEquals(expected.getNormalizedRangeCryptos().get(0).getCryptoName(),
                recommendationService.normalizedRange().getNormalizedRangeCryptos().get(0).getCryptoName());

        assertEquals(expected.getNormalizedRangeCryptos().get(0).getNormalizedRange().toString(),
                recommendationService.normalizedRange().getNormalizedRangeCryptos().get(0).getNormalizedRange().toString());

        assertEquals(expected.getErrorMessage(),
                recommendationService.getStatistics().getErrorMessage());
    }

    @Test
    @Before
    public void testNormalizedRange() {
        MockitoAnnotations.openMocks(this);

        Map<String, List<Crypto>> cryptoMap = new HashMap<>();
        List<Crypto> cryptoList = new ArrayList<>();

        cryptoList.add(TestUtils.createCrypto(1641009600000L, "BTC", 46813.21));
        cryptoList.add(TestUtils.createCrypto(1641020400000L, "BTC", 46979.61));
        cryptoList.add(TestUtils.createCrypto(1641031200000L, "BTC", 47143.98));
        cryptoList.add(TestUtils.createCrypto(1641034800000L, "BTC", 46871.09));

        cryptoMap.put("BTC", cryptoList);
        when(cryptoDataStorage.getCryptoMap()).thenReturn(cryptoMap);

        CryptoNormalizedRangeDTO cryptoNormalizedRangeDTO = new CryptoNormalizedRangeDTO("BTC", BigDecimal.valueOf(0.0070657406));
        List<CryptoNormalizedRangeDTO> cryptoNormalizedRangeDTOList = new ArrayList<>();
        cryptoNormalizedRangeDTOList.add(cryptoNormalizedRangeDTO);

        CryptoNormalizedRangeResponse expected = new CryptoNormalizedRangeResponse();
        expected.setNormalizedRangeCryptos(cryptoNormalizedRangeDTOList);

        assertEquals(expected.getNormalizedRangeCryptos().get(0).getNormalizedRange().toString(),
                recommendationService.normalizedRange(LocalDate.parse("2022-01-01")).
                        getNormalizedRangeCryptos().get(0).getNormalizedRange().toString());

        assertEquals(expected.getNormalizedRangeCryptos().get(0).getCryptoName(),
                recommendationService.normalizedRange(LocalDate.parse("2022-01-01")).
                        getNormalizedRangeCryptos().get(0).getCryptoName());

        assertEquals(expected.getErrorMessage(),
                recommendationService.normalizedRange(LocalDate.parse("2022-01-01")).getErrorMessage());
    }
}
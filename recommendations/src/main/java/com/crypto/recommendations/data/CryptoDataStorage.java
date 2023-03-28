package com.crypto.recommendations.data;

import com.crypto.recommendations.model.Crypto;
import com.crypto.recommendations.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CryptoDataStorage {
    private Map<String, List<Crypto>> cryptoMap;

    @Autowired
    private FilesService filesService;

    @PostConstruct
    public void init() {
        Set<File> files = filesService.findFiles();
        cryptoMap = filesService.readAllFiles(files);
    }

    public Map<String, List<Crypto>> getCryptoMap() {
        return cryptoMap;
    }
}

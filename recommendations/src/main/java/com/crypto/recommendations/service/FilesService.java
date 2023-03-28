package com.crypto.recommendations.service;

import com.crypto.recommendations.model.Crypto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FilesService {

    private static final Logger logger = LoggerFactory.getLogger(FilesService.class);
    private static final String FOLDER_PATH = "C:\\Users\\Alex\\Downloads\\Technical Task\\Prices";

    public Set<File> findFiles() {
        File folder = new File(FOLDER_PATH);
        File[] listOfFiles = folder.listFiles();
        Set<File> files = new HashSet<>();

        if (listOfFiles != null) {
            Collections.addAll(files, listOfFiles);
        } else {
            logger.error("The folder: {} containing the .csv files does not exist", folder.getAbsolutePath());
        }
        return files;
    }

    public List<Crypto> readFile(File file) {
        List<Crypto> cryptoList = new ArrayList<>();

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    cryptoList.add(readLine(line));
                }

            } catch (FileNotFoundException e) {
                logger.error("File {} not found", file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            logger.error("There are no .csv files in the folder {}", file.getAbsolutePath());
        }
        return cryptoList;
    }

    public Crypto readLine(String line) {
        StringTokenizer st = new StringTokenizer(line, ",");

        LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(st.nextToken())),
                TimeZone.getDefault().toZoneId());
        String cryptoName = st.nextToken();
        BigDecimal price = new BigDecimal(st.nextToken());

        return new Crypto(cryptoName, timestamp, price);
    }

    public Map<String, List<Crypto>> readAllFiles(Set<File> files) {
        Map<String, List<Crypto>> map = new HashMap<>();

        for (File file : files) {
            map.put(file.getName().replace("_values.csv", ""), readFile(file));
        }

        return map;
    }
}

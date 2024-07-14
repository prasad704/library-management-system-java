package org.system.librarymanagementsystemjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.system.librarymanagementsystemjava.model.InventoryTransaction;
import org.system.librarymanagementsystemjava.responseEntity.InventoryManagementResponseEntity;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl {

    @Autowired
    @Lazy
    TransactionService transactionService;

    public Optional<InventoryManagementResponseEntity> downloadBook(InventoryTransaction transaction){
                transactionService.save(transaction);
        return Optional.of(new InventoryManagementResponseEntity(HttpStatus.OK.toString(),"Book downloaded", Instant.now()));
    }

    public Optional<Map<String,String>> getPopularByDuration(){
        List<InventoryTransaction> popularListByWeek = transactionService.getBookNameByWeek(Instant.now().minus(Duration.ofDays(7)));
        List<InventoryTransaction> popularListByMonth = transactionService.getBookNameByWeek(Instant.now().minus(Duration.ofDays(30)));
        List<InventoryTransaction> popularListToday = transactionService.getBookNameByWeek(Instant.now().minus(Duration.ofDays(1)));
        Map<String,Integer> mapOfWeek = new HashMap<>();
        for(InventoryTransaction popularByWeek : popularListByWeek){
            if(mapOfWeek.containsKey(popularByWeek.getBookName())){
               int count = mapOfWeek.get(popularByWeek.getBookName());
               mapOfWeek.put(popularByWeek.getBookName(),count+1);
            }else mapOfWeek.put(popularByWeek.getBookName(),1);
        }

        Map<String,Integer> mapOfMonth = new HashMap<>();
        for(InventoryTransaction popularByMonth : popularListByMonth){
            if(mapOfMonth.containsKey(popularByMonth.getBookName())){
                int count = mapOfMonth.get(popularByMonth.getBookName());
                mapOfMonth.put(popularByMonth.getBookName(),count+1);
            }else mapOfMonth.put(popularByMonth.getBookName(),1);
        }
        Map<String,Integer> mapOfDay = new HashMap<>();
        for(InventoryTransaction popularByDay : popularListToday){
            if(mapOfDay.containsKey(popularByDay.getBookName())){
                int count = mapOfDay.get(popularByDay.getBookName());
                mapOfDay.put(popularByDay.getBookName(),count+1);
            }else mapOfDay.put(popularByDay.getBookName(),1);
        }
        Integer maxCountForWeek = 0;
        String popularObjectbyWeek = "";
        for (Map.Entry<String, Integer> entry : mapOfWeek.entrySet()) {
            if (entry.getValue() > maxCountForWeek) {
                maxCountForWeek = entry.getValue();
                popularObjectbyWeek = entry.getKey();
            }
        }
        Integer maxCountForMonth = 0;
        String popularObjectbyMonth = null;
        for (Map.Entry<String, Integer> entry : mapOfMonth.entrySet()) {
            if (entry.getValue() > maxCountForMonth) {
                maxCountForMonth = entry.getValue();
                popularObjectbyMonth = entry.getKey();
            }
        }
        Integer maxCountForDay = 0;
        String popularObjectByDay = null;
        for (Map.Entry<String, Integer> entry : mapOfDay.entrySet()) {
            if (entry.getValue() > maxCountForDay) {
                maxCountForDay = entry.getValue();
                popularObjectByDay = entry.getKey();
            }
        }
        return Optional.of(Map.of("Popular by day",popularObjectByDay,
                "Popular by Week",popularObjectbyWeek,
                "Popular by Month",popularObjectbyMonth));
    }
}

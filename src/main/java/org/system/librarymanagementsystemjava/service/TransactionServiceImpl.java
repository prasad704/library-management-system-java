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
        List<InventoryTransaction> departmentList = transactionService.findAll();
        Map<String,Integer> popularDepartment = new HashMap<>();
        if(departmentList!=null && !departmentList.isEmpty()){
            for(InventoryTransaction  department: departmentList){
                if(popularDepartment.containsKey(department.getDepartmentName())){
                    int count = popularDepartment.get(department.getDepartmentName());
                    popularDepartment.put(department.getDepartmentName(),count+1);
                }else popularDepartment.put(department.getDepartmentName(),1);
            }
        }
        Map<String,Integer> mapOfWeek = new HashMap<>();
        if(popularListByWeek!=null && !popularListByWeek.isEmpty()) {
            for (InventoryTransaction popularByWeek : popularListByWeek) {
                if (mapOfWeek.containsKey(popularByWeek.getBookName())) {
                    int count = mapOfWeek.get(popularByWeek.getBookName());
                    mapOfWeek.put(popularByWeek.getBookName(), count + 1);
                } else mapOfWeek.put(popularByWeek.getBookName(), 1);
            }
        }
        Map<String, Integer> mapOfMonth = new HashMap<>();
        if(popularListByMonth!=null && !popularListByMonth.isEmpty()) {
            for (InventoryTransaction popularByMonth : popularListByMonth) {
                if (mapOfMonth.containsKey(popularByMonth.getBookName())) {
                    int count = mapOfMonth.get(popularByMonth.getBookName());
                    mapOfMonth.put(popularByMonth.getBookName(), count + 1);
                } else mapOfMonth.put(popularByMonth.getBookName(), 1);
            }
        }
        Map<String, Integer> mapOfDay = new HashMap<>();
        if(popularListToday!=null && !popularListToday.isEmpty()) {
            for (InventoryTransaction popularByDay : popularListToday) {
                if (mapOfDay.containsKey(popularByDay.getBookName())) {
                    int count = mapOfDay.get(popularByDay.getBookName());
                    mapOfDay.put(popularByDay.getBookName(), count + 1);
                } else mapOfDay.put(popularByDay.getBookName(), 1);
            }
        }
        Integer maxCountForDepartment = 0;
        String popularDepartmentObject = "No record found";
        if(popularDepartment!=null&&!popularDepartment.isEmpty()) {
            for (Map.Entry<String, Integer> entry : popularDepartment.entrySet()) {
                if (entry.getValue() > maxCountForDepartment) {
                    maxCountForDepartment = entry.getValue();
                    popularDepartmentObject = entry.getKey();
                }
            }
        }
        Integer maxCountForWeek = 0;
        String popularObjectbyWeek = "No record found";
        if(mapOfWeek!=null&&!mapOfWeek.isEmpty()) {
            for (Map.Entry<String, Integer> entry : mapOfWeek.entrySet()) {
                if (entry.getValue() > maxCountForWeek) {
                    maxCountForWeek = entry.getValue();
                    popularObjectbyWeek = entry.getKey();
                }
            }
        }
        Integer maxCountForMonth = 0;
        String popularObjectbyMonth = "No record found";
        if(mapOfMonth!=null && !mapOfMonth.isEmpty()){
        for (Map.Entry<String, Integer> entry : mapOfMonth.entrySet()) {
            if (entry.getValue() > maxCountForMonth) {
                maxCountForMonth = entry.getValue();
                popularObjectbyMonth = entry.getKey();
            }
        }
        }
        Integer maxCountForDay = 0;
        String popularObjectByDay = "No record found";
        if(mapOfDay!=null && !mapOfDay.isEmpty()) {
            for (Map.Entry<String, Integer> entry : mapOfDay.entrySet()) {
                if (entry.getValue() > maxCountForDay) {
                    maxCountForDay = entry.getValue();
                    popularObjectByDay = entry.getKey();
                }
            }
        }
        return Optional.of(Map.of("Popular Department", popularDepartmentObject,
                "Popular by day",popularObjectByDay,
                "Popular by Week",popularObjectbyWeek,
                "Popular by Month",popularObjectbyMonth));
    }
}

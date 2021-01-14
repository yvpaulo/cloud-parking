package com.yvsistemas.cloudparking.service;

import com.yvsistemas.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var id  = getUUID();
        Parking parking = new Parking(id, "JPA-111", "BA", "Ferrari", "Vermelho");
        parkingMap.put(id, parking);
    }
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }
}

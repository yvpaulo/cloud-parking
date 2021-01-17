package com.yvsistemas.cloudparking.service;

import com.yvsistemas.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public Parking findById(String id) {
       return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;

    }
}

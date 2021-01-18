package com.yvsistemas.cloudparking.service;

import com.yvsistemas.cloudparking.exception.ParkingNotException;
import com.yvsistemas.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap();

   /* static {
        var id  = getUUID();
        Parking parking = new Parking(id, "JPA-111", "BA", "Ferrari", "Vermelho");
        parkingMap.put(id, parking);
    }*/

    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ParkingNotException(id);
        }

        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;

    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingUpdate) {
        Parking parking = findById(id);
        parking.setState(parkingUpdate.getState());
        parking.setModel(parkingUpdate.getModel());
        parking.setLicense(parkingUpdate.getLicense());
        parking.setColor(parkingUpdate.getColor());
        parkingMap.replace(id, parking);
        return parking;

    }

    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        //TODo: calcular taxa   Parei no video 12
        parkingMap.replace(id,parking);
        return parking;
    }
}

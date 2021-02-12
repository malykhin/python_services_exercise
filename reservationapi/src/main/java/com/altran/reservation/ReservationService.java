package com.altran.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    static Map<Integer,Integer> slotHashMap=new HashMap<>();
    static Map<String,Integer> cityHashMap=new HashMap<>();

    public boolean checkAvailability(Reservation reservation) {
        if (slotHashMap.isEmpty()) {
            List<Slot> list = slotRepository.findAll();
            slotHashMap = slotRepository.findAll().stream().collect(Collectors.toMap(Slot::getStartTime, Slot::getSid));
        }
        if (cityHashMap.isEmpty()) {
            cityHashMap = cityRepository.findAll().stream().collect(Collectors.toMap(City::getName, City::getCid));
            ;
        }
        try {
            ReservationEntity reservationEntity = new ReservationEntity();
            reservationEntity.setCid(cityHashMap.get(reservation.getCity()));
            reservationEntity.setSid(slotHashMap.get(reservation.getCheckInTime()));
            reservationEntity.setEmailId(reservation.getEmailId());
            reservationEntity = reservationRepository.saveAndFlush(reservationEntity);
            if (reservationEntity.getRid() > 0) {
                return true;
            } else{
                return false;
            }
        } catch (Exception exception) {
            return false;
        }
    }
    public List<Slot> availableSlots(Reservation reservation) {
        // select * from slot where sid not in (select sid from reservation where cid=2)
        return slotRepository.availableSlots(cityHashMap.get(reservation.getCity()));
    }
    }




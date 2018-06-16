package com.reservation.sport.web.rest;

import com.reservation.sport.domain.Reservation;
import com.reservation.sport.repository.ReservationRepository;
import com.reservation.sport.service.ReservationService;
import com.reservation.sport.service.dto.ReservationDTO;
import com.reservation.sport.service.dto.ReservationDataDTO;
import com.reservation.sport.service.dto.ReservationHoursDTO;
import com.reservation.sport.service.dto.ReservationPersistDTO;
import io.undertow.util.BadRequestException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationResource {

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/availableDates")
    public ResponseEntity<ReservationDataDTO> getAvailableDates(){
        return new ResponseEntity<>(new ReservationDataDTO(reservationService.getAvailableDates(), "SUCCESS",""), HttpStatus.OK);
    }

    @GetMapping("/reservesByUser")
    public ResponseEntity<List<Reservation>> getReservesByUser(Long userId){
        return new ResponseEntity<>(reservationService.getReservationsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/reservesByObject")
    public ResponseEntity<List<Reservation>> getReservesByObject(Long objectDefinitionId){
        return new ResponseEntity<>(reservationService.getReservationsByObjectDefinition(objectDefinitionId), HttpStatus.OK);
    }

    @GetMapping("/availableHours")
    public ResponseEntity<ReservationHoursDTO> getAvailableHours(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate selectedDate,
                                                                 @RequestParam Long objectDefinitionId){
        return new ResponseEntity<>(new ReservationHoursDTO(reservationService.getAvailableHours(selectedDate, objectDefinitionId), "SUCCESS",""), HttpStatus.OK);
    }

//    @PostMapping("/reserve")
//    public ResponseEntity<ReservationDTO> getAvailableHours(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate selectedDate,
//                                                            @RequestParam String selectedTime,
//                                                            @RequestParam Long objectDefinitionId) throws BadRequestException {
//        String[] split = selectedTime.split(":");
//        LocalTime time = LocalTime.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
//        return new ResponseEntity<>(reservationService.saveReservation(selectedDate, time, objectDefinitionId), HttpStatus.OK);
//    }

    @PostMapping("/reserve")
    public ResponseEntity<ReservationDTO> getAvailableHours(@RequestBody ReservationPersistDTO reservationPersistDTO) throws BadRequestException {
        String[] split = reservationPersistDTO.getSelectedHour().split(":");
        LocalTime time = LocalTime.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        return new ResponseEntity<>(reservationService.saveReservation(reservationPersistDTO.getSelectedDate(), time, reservationPersistDTO.getObjectDefinitionId()), HttpStatus.OK);
    }

}

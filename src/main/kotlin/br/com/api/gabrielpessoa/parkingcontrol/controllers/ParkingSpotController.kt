package br.com.api.gabrielpessoa.parkingcontrol.controllers

import br.com.api.gabrielpessoa.parkingcontrol.domain.models.ParkingSpotModel
import br.com.api.gabrielpessoa.parkingcontrol.services.ParkingSpotService
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId


@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/parkingspot")
class ParkingSpotController(
    private val parkingSpotService: ParkingSpotService
) {

    @PostMapping
    fun saveParkingSpot(@RequestBody @Valid parkingSpotModel: ParkingSpotModel): ResponseEntity<out Serializable> {

        if (parkingSpotService.existsByLicencePlateCar(parkingSpotModel.licensePlateCar)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Conflict: License Plate Car is already in use!")
        }
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotModel.parkingSpotNumber)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Conflict: Parking Spot is already in use!")
        }
        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotModel.apartment, parkingSpotModel.block)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Conflict: Parking Spot already registered for this apartment/block!")
        }
        parkingSpotModel.registrationDate = LocalDateTime.now(ZoneId.of("UTC"))
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(parkingSpotService.save(parkingSpotModel))
    }

    @GetMapping
    fun getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = arrayOf("id"),
            direction = Sort.Direction.ASC
        ) pageable: Pageable): ResponseEntity<Page<ParkingSpotModel>> {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable))
    }



}
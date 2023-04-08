package br.com.api.gabrielpessoa.parkingcontrol.controllers

import br.com.api.gabrielpessoa.parkingcontrol.domain.models.ParkingSpotModel
import br.com.api.gabrielpessoa.parkingcontrol.services.ParkingSpotService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


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
    fun getAllParkingSpots(
        @PageableDefault(
            page = 0, size = 10, sort = arrayOf("id"),
            direction = Sort.Direction.ASC
        ) pageable: Pageable
    ): ResponseEntity<Page<ParkingSpotModel>> {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable))
    }

    @GetMapping("/{id}")
    fun getOneParkingSpot(@PathVariable(value = "id") id: UUID): ResponseEntity<out Serializable> {
        val parkingSpotModelOptional: Optional<ParkingSpotModel> = parkingSpotService.findById(id)
        if (parkingSpotModelOptional.isEmpty) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.")
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get())
    }

    @DeleteMapping("/{id}")
    fun deleteParkingSpot(@PathVariable(value = "id") id: UUID): ResponseEntity<out Serializable> {
        val parkingSpotModelOptional: Optional<ParkingSpotModel> = parkingSpotService.findById(id)
        if (parkingSpotModelOptional.isEmpty) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.")
        }
        parkingSpotService.delete(parkingSpotModelOptional.get())
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot delete successfully.")
    }

    @PutMapping("/{id}")
    fun updateParkingSpot(
        @PathVariable(value = "id") id: UUID,
        @RequestBody parkingSpotModel: ParkingSpotModel
    ): ResponseEntity<out Serializable> {
        val parkingSpotModelOptional: Optional<ParkingSpotModel> = parkingSpotService.findById(id)
        if (parkingSpotModelOptional.isEmpty) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.")
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel))
    }


}
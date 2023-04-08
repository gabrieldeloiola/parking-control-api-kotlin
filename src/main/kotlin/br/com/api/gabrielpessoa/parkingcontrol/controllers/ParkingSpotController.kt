package br.com.api.gabrielpessoa.parkingcontrol.controllers

import br.com.api.gabrielpessoa.parkingcontrol.domain.models.ParkingSpotModel
import br.com.api.gabrielpessoa.parkingcontrol.dto.ParkingSpotDto
import br.com.api.gabrielpessoa.parkingcontrol.services.ParkingSpotService
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/parkingspot")
class ParkingSpotController(
    private val parkingSpotService: ParkingSpotService
) {

    @PostMapping
    fun saveParkingSpot(@RequestBody @Valid parkingSpotDto: ParkingSpotDto): ResponseEntity<ParkingSpotModel> {
        val parkingSpotModel = ParkingSpotModel()
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel)
        parkingSpotModel.registrationDate = LocalDateTime.now(ZoneId.of("UTC"))
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel)

        )
    }
}
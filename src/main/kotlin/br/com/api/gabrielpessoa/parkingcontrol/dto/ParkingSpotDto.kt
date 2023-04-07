package br.com.api.gabrielpessoa.parkingcontrol.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ParkingSpotDto(

    @NotBlank
    private val parkingSpotNumber: String,

    @NotBlank
    @Size(max = 7)
    private val licensePlateCar: String,

    @NotBlank
    private val brandCar: String,

    @NotBlank
    private val modelCar: String,

    @NotBlank
    private val colorCar: String,

    @NotBlank
    private val responsibleName: String,

    @NotBlank
    private val apartment: String,

    @NotBlank
    private val block: String
)
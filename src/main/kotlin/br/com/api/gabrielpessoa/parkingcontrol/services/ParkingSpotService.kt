package br.com.api.gabrielpessoa.parkingcontrol.services

import br.com.api.gabrielpessoa.parkingcontrol.domain.models.ParkingSpotModel
import br.com.api.gabrielpessoa.parkingcontrol.repositories.ParkingSpotRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {

    @Transactional
    fun save(parkingSpotModel: ParkingSpotModel): ParkingSpotModel {
        return parkingSpotRepository.save(parkingSpotModel)
    }

    fun existsByLicencePlateCar(licensePlateCar: String): Boolean {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar)
    }

    fun existsByParkingSpotNumber(parkingSpotNumber: String): Boolean {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber)
    }

    fun existsByApartmentAndBlock(apartment: String, block: String): Boolean {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block)
    }

    fun findAll(pageable: Pageable): Page<ParkingSpotModel> {
        return parkingSpotRepository.findAll(pageable)
    }

    fun findById(id: UUID): Optional<ParkingSpotModel> {
        return parkingSpotRepository.findById(id)
    }

    @Transactional
    fun delete(parkingSpotModel: ParkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel)
    }

}
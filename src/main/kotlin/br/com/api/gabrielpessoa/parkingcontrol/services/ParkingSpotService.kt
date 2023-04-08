package br.com.api.gabrielpessoa.parkingcontrol.services

import br.com.api.gabrielpessoa.parkingcontrol.domain.models.ParkingSpotModel
import br.com.api.gabrielpessoa.parkingcontrol.repositories.ParkingSpotRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {

    @Transactional
    fun save(parkingSpotModel: ParkingSpotModel): ParkingSpotModel{
        return parkingSpotRepository.save(parkingSpotModel)
    }


}
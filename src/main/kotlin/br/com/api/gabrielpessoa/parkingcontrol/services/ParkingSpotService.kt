package br.com.api.gabrielpessoa.parkingcontrol.services

import br.com.api.gabrielpessoa.parkingcontrol.repositories.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {


}
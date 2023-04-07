package br.com.api.gabrielpessoa.parkingcontrol.repositories

import br.com.api.gabrielpessoa.parkingcontrol.domain.models.ParkingSpotModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ParkingSpotRepository : JpaRepository<ParkingSpotModel, UUID> {
}
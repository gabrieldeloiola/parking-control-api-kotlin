package br.com.api.gabrielpessoa.parkingcontrol.domain.models

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "TB_PARKING_SPOT", schema = "PARKING_SPOT_SCHEMA")
data class ParkingSpotModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true, length = 10)
    val parkingSpotNumber: String = "",

    @Column(nullable = false, unique = true, length = 7)
    val licensePlateCar: String = "",

    @Column(nullable = false, length = 70)
    val brandCar: String = "",

    @Column(nullable = false, length = 70)
    val modelCar: String = "",

    @Column(nullable = false, length = 70)
    val colorCar: String = "",

    @Column(nullable = false)
    var registrationDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false, length = 130)
    val responsibleName: String = "",

    @Column(nullable = false, length = 30)
    val apartment: String = "",

    @Column(nullable = false, length = 30)
    val block: String = "",


    ) : Serializable {

    val serialVersionUID: Long = 1
}

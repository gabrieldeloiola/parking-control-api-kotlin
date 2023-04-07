package br.com.api.gabrielpessoa.parkingcontrol.domain.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "TB_PARKING_SPOT")
@NoArgsConstructor
@AllArgsConstructor
data class ParkingSpotModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,
    @Column(nullable = false, unique = true, length = 10)
    val parkingSpotNumber: String,
    @Column(nullable = false, unique = true, length = 7)
    val licensePlateCar: String,
    @Column(nullable = false, length = 70)
    val brandCar: String,
    @Column(nullable = false, length = 70)
    val modelCar: String,
    @Column(nullable = false, length = 70)
    val colorCar: String,
    @Column(nullable = false)
    var registrationDate: LocalDateTime,
    @Column(nullable = false, length = 130)
    val responsibleName: String,
    @Column(nullable = false, length = 30)
    val apartment: String,
    @Column(nullable = false, length = 30)
    val block: String

) : Serializable {

    private val serialVersionUID: Long = 1L

    constructor() : this(
        UUID.randomUUID(),
        "",
        "",
        "",
        "",
        "",
        LocalDateTime.now(),
        "",
        "",
        ""
    )


}

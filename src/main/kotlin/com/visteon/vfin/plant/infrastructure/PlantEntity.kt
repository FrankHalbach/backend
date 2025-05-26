package com.visteon.vfin.plant.infrastructure

import com.visteon.vfin.common.types.EmailAddress
import com.visteon.vfin.common.types.EmailAddress.Companion.invoke
import com.visteon.vfin.common.types.NameField
import com.visteon.vfin.common.types.NameField.Companion.invoke
import com.visteon.vfin.plant.domain.Plant
import com.visteon.vfin.plant.domain.PlantId
import com.visteon.vfin.users.domain.AppUser
import com.visteon.vfin.users.domain.AppUserId
import com.visteon.vfin.users.domain.AppUserId.Companion.invoke
import com.visteon.vfin.users.infrastructure.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="plant")
@Entity
data class PlantEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null ,

    @Column(nullable = false, unique = true)
    val code: String = "",

    @Column(nullable = false)
    val name: String = ""

)


// Domain ↔ Entity
fun PlantEntity.toDomain(): Plant =
    Plant(
        plantId = PlantId(this.id ?: 0),
        code = NameField(this.code),
        name = NameField(this.name),

    )

fun Plant.toEntity(id:Int?): PlantEntity =
    PlantEntity(
        id = id, // Let DB auto-generate, or pass externally if needed
        code= this.code.value,
        name=this.name.value
    )
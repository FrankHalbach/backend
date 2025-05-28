package com.visteon.vfin.plant.infrastructure

import jakarta.persistence.*

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
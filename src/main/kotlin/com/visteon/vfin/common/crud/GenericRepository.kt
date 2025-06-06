package com.visteon.vfin.common.crud

import org.springframework.data.repository.CrudRepository

interface GenericRepository<Domain : EntityId<ID>, Entity : Any, ID> {
    fun save(domain: Domain): Domain
    fun findById(id: ID): Domain?
    fun findAll(): List<Domain>
}

abstract class BaseGenericRepository<Domain : EntityId<ID>, Entity : Any, ID, DBID : Any>(
    private val repo: CrudRepository<Entity, DBID>,
    private val toEntity: (Domain) -> Entity,
    private val toDomain: (Entity) -> Domain,
    private val idToDb: (ID) -> DBID
) : GenericRepository<Domain, Entity, ID> {

    override fun save(domain: Domain): Domain {
        val entity = toEntity(domain)
        val saved = repo.save(entity)
        return toDomain(saved)
    }

    override fun findById(id: ID): Domain? =
        repo.findById(idToDb(id)).orElse(null)?.let { toDomain(it) }

    override fun findAll(): List<Domain> =
        repo.findAll().map { toDomain(it) }

}
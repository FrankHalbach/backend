//package com.visteon.vfin.common.crud
//
//interface CrudService<Domain, ID> {
//    fun create(domain: Domain): Domain
//    fun getById(id: ID): Domain?
//    fun getAll(): List<Domain>
//    fun update(domain: Domain): Domain
//}
//
//abstract class GenericBaseService<Domain : EntityId<ID>, ID : Any>(
//    private val repo: GenericRepository<Domain, *, ID>
//) : CrudService<Domain, ID> {
//    override fun create(domain: Domain): Domain = repo.save(domain)
//    override fun getById(id: ID): Domain? = repo.findById(id)
//    override fun getAll(): List<Domain> = repo.findAll()
//    override fun update(domain: Domain): Domain {
//       repo.findById(domain.id) ?: throw NoSuchElementException("Entity with ID ${domain.id} not found")
//       return repo.save(domain)
//   }
//}
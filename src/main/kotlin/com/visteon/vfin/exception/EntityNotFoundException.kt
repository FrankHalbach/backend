package com.visteon.vfin.exception

class EntityNotFoundException(entity: String, id: String) :
    DomainException("$entity with id '$id' not found")
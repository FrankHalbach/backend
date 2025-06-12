package com.visteon.vfin.common.exception

class EntityNotFoundException(entity: String, id: String) :
    DomainException("$entity with id '$id' not found")
package com.visteon.vfin.common.exception

class DuplicateEntityException(entity: String, keyName: String, keyValue: String) :
    DomainException("$entity with $keyName '$keyValue' already exists", "DUPLICATED_${entity.uppercase()}")



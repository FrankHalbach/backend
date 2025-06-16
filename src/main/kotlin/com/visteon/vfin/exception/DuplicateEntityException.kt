package com.visteon.vfin.exception

class DuplicateEntityException(entity: String, keyName: String, keyValue: String) :
    DomainException("$entity with $keyName '$keyValue' already exists")



package com.visteon.vfin.exception

class EmailAlreadyTakenException(
    emailAddress: String
) : DomainException("User with email '$emailAddress' already exists")
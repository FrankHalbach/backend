package com.visteon.vfin.common.exception

class EmailAlreadyTakenException(
    emailAddress: String
) : DomainException("User with email '$emailAddress' already exists", "DUPLICATE_USER_EMAIL")
package com.visteon.vfin.common.auditlog

import com.fasterxml.jackson.databind.ObjectMapper
import com.visteon.vfin.common.Ids
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import java.util.*


/*
@Aspect
@Component
class AuditLogAspect(
    private val auditLogRepository: AuditLogRepository,
    private val objectMapper: ObjectMapper
) {

    @Pointcut(
        "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
                "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
                "@annotation(org.springframework.web.bind.annotation.DeleteMapping)"
    )
    fun httpModifyingMethods() {}

    @AfterReturning("httpModifyingMethods()")
    fun logAction(joinPoint: JoinPoint) {
        val method = joinPoint.signature.name
        val args = joinPoint.args

        val body = try {
            objectMapper.writeValueAsString(args)
        } catch (e: Exception) {
            "Could not serialize args: ${e.message}"
        }

        val log = AuditLog(
            userId = getUserIdFromSecurityContextOrDefault(),
            httpMethod = method,
            requestPath = joinPoint.target.javaClass.simpleName + "." + method,
            requestType = "METHOD",
            requestBody = body
        )

        auditLogRepository.save(log)
    }

    private fun getUserIdFromSecurityContextOrDefault(): UUID {
        // Hook into SecurityContextHolder if applicable
        return Ids.empty()
    }
}*/

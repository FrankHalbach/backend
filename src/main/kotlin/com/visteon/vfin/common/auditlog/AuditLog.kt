package com.visteon.vfin.common.auditlog

//import com.visteon.vfin.common.Ids
//import jakarta.persistence.*
//import java.time.Instant
//import java.util.UUID

//@Entity
//@Table(name = "audit_log")
//data class AuditLog(
//
//    @Id
//    @Column(name = "id", nullable = false)
//    val id: UUID = Ids.new(),
//
//    @Column(name = "user_id",nullable = false)
//    val userId: UUID = Ids.empty(),
//
//    //@Column(name = "http_method", nullable = false)
//    //val httpMethod: String = "",
//
//    //@Column(name = "request_path", nullable = false)
//    //val requestPath: String = "",
//
//    @Column(name = "request_type", nullable = false)
//    val requestType: String = "",
//
//    @Lob
//    @Column(name = "request_body")
//    val requestBody: String = "",
//
//    @Column(name = "timestamp", nullable = false)
//    val timestamp: Instant = Instant.now() // todo: build central clock
//)

package com.visteon.vfin.configuration

import com.visteon.vfin.plant.infrastructure.PlantEntity
import com.visteon.vfin.project.infrastructure.ProjectEntity
import com.visteon.vfin.users.infrastructure.AppUserEntity
import com.visteon.vfin.users.infrastructure.AppUserRolesEntity
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
@Transactional
@Profile("dev")
class DevSchemaInitializer : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        SchemaUtils.create(
            AppUserEntity,
            AppUserRolesEntity,
            ProjectEntity,
            PlantEntity,


            )
    }

}
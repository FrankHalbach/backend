package com.visteon.vfin.configuration

import com.visteon.vfin.currency.CurrencySchema
import com.visteon.vfin.exchangerate.ExchangeRateSchema
import com.visteon.vfin.plant.PlantSchema
import com.visteon.vfin.project.ProjectSchema
import com.visteon.vfin.user.UserSchema
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
@Transactional
@Profile("dev","test")
class DevTestSchemaInitializer : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        UserSchema.initialize()
        ProjectSchema.initialize()
        PlantSchema.initialize()
        CurrencySchema.initialize()
        ExchangeRateSchema.initialize()

    }

}
package com.visteon.vfin.configuration


import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class InitDev(val order: Int = 0)  // For controlling execution order

/*
Scans assembly for @InitDev and executes in order
* */
@Transactional
@Component
@Profile("dev", "test")
class InitDevRunner(
    private val applicationContext: ApplicationContext
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val beans = applicationContext.getBeansWithAnnotation(Component::class.java)

        val initMethods = beans.values
            .flatMap { bean ->
                bean::class.members
                    .filterIsInstance<KFunction<*>>()
                    .mapNotNull { function ->
                        function.findAnnotation<InitDev>()?.let { annotation ->
                            InitMethod(bean, function, annotation.order)
                        }
                    }
            }
            .sortedBy { it.order }

        initMethods.forEach { (bean, function, _) ->
            function.call(bean)
        }
    }

    private data class InitMethod(
        val bean: Any,
        val function: KFunction<*>,
        val order: Int
    )
}
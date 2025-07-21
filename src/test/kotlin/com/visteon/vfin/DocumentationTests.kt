package com.visteon.vfin

import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter
import kotlin.test.Test

/*
    This is generating UML files under build / spring-modulith-docs
*/
class DocumentationTests {

    private val modules = ApplicationModules.of(VFinApplication::class.java)

    @Test
    fun writeDocumentationSnippets() {

        Documenter(modules)
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml()
    }

}
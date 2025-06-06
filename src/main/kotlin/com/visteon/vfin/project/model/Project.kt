package com.visteon.vfin.project.model

import com.visteon.vfin.common.Ids
import com.visteon.vfin.common.crud.EntityId
import com.visteon.vfin.common.types.AuditInfo
import com.visteon.vfin.common.types.NameField

import java.util.*

data class Project (
    override val id: ProjectId,
    val name: NameField,
    //val audit: AuditInfo,
): EntityId<ProjectId> {
    fun updateFrom(updated:Project) : Project {
        require(this.id == updated.id) { "Cannot update: ID mismatch" }
        return this.copy(name = updated.name)
    }
    companion object {
        fun from(id: String, name: String): Project {
            return Project(
                id = ProjectId.from(id),
                name = NameField(name),
                //audit = AuditInfo()
            )
        }
        fun new(name: String) : Project {
            return Project(
                id = ProjectId.new(),
                name = NameField(name))
        }
    }

}







@JvmInline
value class ProjectId(val value: UUID) {
    companion object {
        fun new() = ProjectId(Ids.new())
        fun from(id:String) = ProjectId(UUID.fromString(id))
    }
}
/*
 * This file is generated by jOOQ.
 */
package com.kotlinspring.infrastructure.db.tables.pojos


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.io.Serializable
import java.time.OffsetDateTime


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
@Entity
@Table(
    name = "_prisma_migrations",
    schema = "public"
)
data class _PrismaMigrations(
    @get:Id
    @get:Column(name = "id", nullable = false, length = 36)
    var id: String,
    @get:Column(name = "checksum", nullable = false, length = 64)
    var checksum: String,
    @get:Column(name = "finished_at", precision = 6)
    var finishedAt: OffsetDateTime? = null,
    @get:Column(name = "migration_name", nullable = false, length = 255)
    var migrationName: String,
    @get:Column(name = "logs")
    var logs: String? = null,
    @get:Column(name = "rolled_back_at", precision = 6)
    var rolledBackAt: OffsetDateTime? = null,
    @get:Column(name = "started_at", precision = 6)
    var startedAt: OffsetDateTime? = null,
    @get:Column(name = "applied_steps_count")
    var appliedStepsCount: Int? = null
): Serializable {


    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (this::class != other::class)
            return false
        val o: _PrismaMigrations = other as _PrismaMigrations
        if (this.id != o.id)
            return false
        if (this.checksum != o.checksum)
            return false
        if (this.finishedAt == null) {
            if (o.finishedAt != null)
                return false
        }
        else if (this.finishedAt != o.finishedAt)
            return false
        if (this.migrationName != o.migrationName)
            return false
        if (this.logs == null) {
            if (o.logs != null)
                return false
        }
        else if (this.logs != o.logs)
            return false
        if (this.rolledBackAt == null) {
            if (o.rolledBackAt != null)
                return false
        }
        else if (this.rolledBackAt != o.rolledBackAt)
            return false
        if (this.startedAt == null) {
            if (o.startedAt != null)
                return false
        }
        else if (this.startedAt != o.startedAt)
            return false
        if (this.appliedStepsCount == null) {
            if (o.appliedStepsCount != null)
                return false
        }
        else if (this.appliedStepsCount != o.appliedStepsCount)
            return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + this.id.hashCode()
        result = prime * result + this.checksum.hashCode()
        result = prime * result + (if (this.finishedAt == null) 0 else this.finishedAt.hashCode())
        result = prime * result + this.migrationName.hashCode()
        result = prime * result + (if (this.logs == null) 0 else this.logs.hashCode())
        result = prime * result + (if (this.rolledBackAt == null) 0 else this.rolledBackAt.hashCode())
        result = prime * result + (if (this.startedAt == null) 0 else this.startedAt.hashCode())
        result = prime * result + (if (this.appliedStepsCount == null) 0 else this.appliedStepsCount.hashCode())
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder("_PrismaMigrations (")

        sb.append(id)
        sb.append(", ").append(checksum)
        sb.append(", ").append(finishedAt)
        sb.append(", ").append(migrationName)
        sb.append(", ").append(logs)
        sb.append(", ").append(rolledBackAt)
        sb.append(", ").append(startedAt)
        sb.append(", ").append(appliedStepsCount)

        sb.append(")")
        return sb.toString()
    }
}

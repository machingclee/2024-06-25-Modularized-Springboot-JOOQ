/*
 * This file is generated by jOOQ.
 */
package com.kotlinspring.infrastructure.db.tables


import com.kotlinspring.infrastructure.db.Public
import com.kotlinspring.infrastructure.db.keys._PRISMA_MIGRATIONS_PKEY
import com.kotlinspring.infrastructure.db.tables.records._PrismaMigrationsRecord

import java.time.OffsetDateTime

import kotlin.collections.Collection

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.PlainSQL
import org.jooq.QueryPart
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class _PrismaMigrations(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, _PrismaMigrationsRecord>?,
    parentPath: InverseForeignKey<out Record, _PrismaMigrationsRecord>?,
    aliased: Table<_PrismaMigrationsRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<_PrismaMigrationsRecord>(
    alias,
    Public.PUBLIC,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table(),
    where,
) {
    companion object {

        /**
         * The reference instance of <code>public._prisma_migrations</code>
         */
        val _PRISMA_MIGRATIONS: _PrismaMigrations = _PrismaMigrations()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<_PrismaMigrationsRecord> = _PrismaMigrationsRecord::class.java

    /**
     * The column <code>public._prisma_migrations.id</code>.
     */
    val ID: TableField<_PrismaMigrationsRecord, String?> = createField(DSL.name("id"), SQLDataType.VARCHAR(36).nullable(false), this, "")

    /**
     * The column <code>public._prisma_migrations.checksum</code>.
     */
    val CHECKSUM: TableField<_PrismaMigrationsRecord, String?> = createField(DSL.name("checksum"), SQLDataType.VARCHAR(64).nullable(false), this, "")

    /**
     * The column <code>public._prisma_migrations.finished_at</code>.
     */
    val FINISHED_AT: TableField<_PrismaMigrationsRecord, OffsetDateTime?> = createField(DSL.name("finished_at"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "")

    /**
     * The column <code>public._prisma_migrations.migration_name</code>.
     */
    val MIGRATION_NAME: TableField<_PrismaMigrationsRecord, String?> = createField(DSL.name("migration_name"), SQLDataType.VARCHAR(255).nullable(false), this, "")

    /**
     * The column <code>public._prisma_migrations.logs</code>.
     */
    val LOGS: TableField<_PrismaMigrationsRecord, String?> = createField(DSL.name("logs"), SQLDataType.CLOB, this, "")

    /**
     * The column <code>public._prisma_migrations.rolled_back_at</code>.
     */
    val ROLLED_BACK_AT: TableField<_PrismaMigrationsRecord, OffsetDateTime?> = createField(DSL.name("rolled_back_at"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "")

    /**
     * The column <code>public._prisma_migrations.started_at</code>.
     */
    val STARTED_AT: TableField<_PrismaMigrationsRecord, OffsetDateTime?> = createField(DSL.name("started_at"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "")

    /**
     * The column <code>public._prisma_migrations.applied_steps_count</code>.
     */
    val APPLIED_STEPS_COUNT: TableField<_PrismaMigrationsRecord, Int?> = createField(DSL.name("applied_steps_count"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.INTEGER)), this, "")

    private constructor(alias: Name, aliased: Table<_PrismaMigrationsRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<_PrismaMigrationsRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<_PrismaMigrationsRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public._prisma_migrations</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public._prisma_migrations</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public._prisma_migrations</code> table reference
     */
    constructor(): this(DSL.name("_prisma_migrations"), null)
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getPrimaryKey(): UniqueKey<_PrismaMigrationsRecord> = _PRISMA_MIGRATIONS_PKEY
    override fun `as`(alias: String): _PrismaMigrations = _PrismaMigrations(DSL.name(alias), this)
    override fun `as`(alias: Name): _PrismaMigrations = _PrismaMigrations(alias, this)
    override fun `as`(alias: Table<*>): _PrismaMigrations = _PrismaMigrations(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): _PrismaMigrations = _PrismaMigrations(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): _PrismaMigrations = _PrismaMigrations(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): _PrismaMigrations = _PrismaMigrations(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): _PrismaMigrations = _PrismaMigrations(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): _PrismaMigrations = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): _PrismaMigrations = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): _PrismaMigrations = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): _PrismaMigrations = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): _PrismaMigrations = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): _PrismaMigrations = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): _PrismaMigrations = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): _PrismaMigrations = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): _PrismaMigrations = where(DSL.notExists(select))
}

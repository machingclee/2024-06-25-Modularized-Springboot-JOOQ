/*
 * This file is generated by jOOQ.
 */
package com.kotlinspring.infrastructure.db.tables


import com.kotlinspring.infrastructure.db.Public
import com.kotlinspring.infrastructure.db.keys.COURSE__COURSE_TEACHERID_FKEY
import com.kotlinspring.infrastructure.db.keys.TEACHER_PKEY
import com.kotlinspring.infrastructure.db.tables.Course.CoursePath
import com.kotlinspring.infrastructure.db.tables.records.TeacherRecord

import kotlin.collections.Collection

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
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
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Teacher(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, TeacherRecord>?,
    parentPath: InverseForeignKey<out Record, TeacherRecord>?,
    aliased: Table<TeacherRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<TeacherRecord>(
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
         * The reference instance of <code>public.Teacher</code>
         */
        val TEACHER: Teacher = Teacher()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<TeacherRecord> = TeacherRecord::class.java

    /**
     * The column <code>public.Teacher.id</code>.
     */
    val ID: TableField<TeacherRecord, Int?> = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.Teacher.name</code>.
     */
    val NAME: TableField<TeacherRecord, String?> = createField(DSL.name("name"), SQLDataType.VARCHAR(30).nullable(false), this, "")

    private constructor(alias: Name, aliased: Table<TeacherRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<TeacherRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<TeacherRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.Teacher</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.Teacher</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.Teacher</code> table reference
     */
    constructor(): this(DSL.name("Teacher"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, TeacherRecord>?, parentPath: InverseForeignKey<out Record, TeacherRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, TEACHER, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class TeacherPath : Teacher, Path<TeacherRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, TeacherRecord>?, parentPath: InverseForeignKey<out Record, TeacherRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<TeacherRecord>): super(alias, aliased)
        override fun `as`(alias: String): TeacherPath = TeacherPath(DSL.name(alias), this)
        override fun `as`(alias: Name): TeacherPath = TeacherPath(alias, this)
        override fun `as`(alias: Table<*>): TeacherPath = TeacherPath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIdentity(): Identity<TeacherRecord, Int?> = super.getIdentity() as Identity<TeacherRecord, Int?>
    override fun getPrimaryKey(): UniqueKey<TeacherRecord> = TEACHER_PKEY

    private lateinit var _course: CoursePath

    /**
     * Get the implicit to-many join path to the <code>public.Course</code>
     * table
     */
    fun course(): CoursePath {
        if (!this::_course.isInitialized)
            _course = CoursePath(this, null, COURSE__COURSE_TEACHERID_FKEY.inverseKey)

        return _course;
    }

    val course: CoursePath
        get(): CoursePath = course()
    override fun `as`(alias: String): Teacher = Teacher(DSL.name(alias), this)
    override fun `as`(alias: Name): Teacher = Teacher(alias, this)
    override fun `as`(alias: Table<*>): Teacher = Teacher(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Teacher = Teacher(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Teacher = Teacher(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Teacher = Teacher(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): Teacher = Teacher(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): Teacher = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): Teacher = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): Teacher = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): Teacher = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): Teacher = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): Teacher = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): Teacher = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): Teacher = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): Teacher = where(DSL.notExists(select))
}
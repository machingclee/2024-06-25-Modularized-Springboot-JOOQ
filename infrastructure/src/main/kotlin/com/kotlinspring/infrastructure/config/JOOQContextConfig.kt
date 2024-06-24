package com.kotlinspring.infrastructure.com.kotlinspring.infrastructure.config

import org.jooq.DSLContext
import org.jooq.ExecuteContext
import org.jooq.ExecuteListener
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultExecuteListenerProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator
import org.springframework.jdbc.support.SQLExceptionTranslator
import javax.sql.DataSource


@Configuration
open class JOOQContextConfig {
    @Value("\${spring.datasource.driver-class-name}")
    lateinit var driverClassName: String

    @Value("\${spring.datasource.url}")
    lateinit var url: String

    @Value("\${spring.datasource.password}")
    lateinit var username: String

    @Value("\${spring.datasource.password}")
    lateinit var password: String

    @Value("\${spring.jooq.sql-dialect}")
    lateinit var sqlDialectName: String

    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(driverClassName)
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password
        return dataSource
    }


    @Bean
    open fun getDSLContext(dataSource: DataSource): DSLContext {
        return DSL.using(dataSource, SQLDialect.POSTGRES)
    }

    @Bean
    open fun exceptionTransformer(): ExceptionTranslator {
        return ExceptionTranslator()
    }

    @Bean
    open fun configuration(connectionProvider: DataSourceConnectionProvider): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(connectionProvider)
        jooqConfiguration.set(DefaultExecuteListenerProvider(exceptionTransformer()))

        val dialect = SQLDialect.valueOf(sqlDialectName)
        jooqConfiguration.set(dialect)

        return jooqConfiguration
    }
}

class ExceptionTranslator : ExecuteListener {
    override fun exception(context: ExecuteContext) {
        val dialect = context.configuration().dialect()
        val translator
                : SQLExceptionTranslator = SQLErrorCodeSQLExceptionTranslator(dialect.name)
        context.exception(
            translator
                .translate("Access database using Jooq", context.sql(), context.sqlException()!!)
        )
    }
}
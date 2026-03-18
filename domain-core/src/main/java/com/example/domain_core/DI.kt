package com.example.domain_core

import com.example.domain_core.usecase.GetCurrentLocationUseCase
import org.koin.dsl.module

val domainCoreModule = module {
    factory { GetCurrentLocationUseCase(repository = get()) }
}

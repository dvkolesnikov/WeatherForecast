package com.example.date_core

import com.example.domain_core.repository.LocationRepository
import org.koin.dsl.module

val dataCoreModule = module {
    single<LocationRepository> {
        LocationRepositoryImpl(context = get())
    }
}

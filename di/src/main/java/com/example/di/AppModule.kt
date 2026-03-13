package com.example.di

import com.example.core_network.coreNetworkModule
import org.koin.dsl.module

val appModule = module {
    includes(
        coreNetworkModule,
    )
}

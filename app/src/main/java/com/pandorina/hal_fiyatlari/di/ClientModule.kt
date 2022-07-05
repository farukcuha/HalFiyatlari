package com.pandorina.hal_fiyatlari.di

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.logging.*
import org.koin.dsl.module

val clientModule = module {
    single {
        HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){

            }
        }
    }
}
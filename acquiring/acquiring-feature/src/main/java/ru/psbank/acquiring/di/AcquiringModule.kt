package ru.psbank.acquiring.di

import dagger.Module

@Module(includes = [AcquiringInternalModule::class])
interface AcquiringModule

package ru.psbank.currencyoperations.di

import dagger.Module

@Module(includes = [CurrencyOperationsInternalModule::class])
interface CurrencyOperationsModule

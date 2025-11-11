package ru.psbank.bookkeeping.di

import dagger.Module

@Module(includes = [BookkeepingInternalModule::class])
interface BookkeepingModule

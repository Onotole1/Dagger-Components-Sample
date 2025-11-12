package ru.psbank.modules

import ru.psbank.currencyoperations.di.CreateOperationFragmentDependencies
import ru.psbank.currencyoperations.di.OperationsListFragmentDependencies
import ru.psbank.mainscreen.di.MainScreenFragmentDependencies

internal interface ApplicationComponentDependencies : OperationsListFragmentDependencies,
    CreateOperationFragmentDependencies,
    MainScreenFragmentDependencies

package ru.psbank.acquiringoffice.core

import androidx.fragment.app.Fragment

interface AcquiringOfficeStarter {
    fun createTerminalFragment(): Fragment
}
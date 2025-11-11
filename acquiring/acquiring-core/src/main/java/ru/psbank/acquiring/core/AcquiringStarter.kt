package ru.psbank.acquiring.core

import androidx.fragment.app.Fragment

interface AcquiringStarter {
    fun createAcquiringFragment(): Fragment
}
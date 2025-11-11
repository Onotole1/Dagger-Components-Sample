package ru.psbank.bookkeeping.core

import androidx.fragment.app.Fragment

interface BookkeepingStarter {
    fun createBookkeepingFragment(): Fragment
}
package ru.psbank.bookkeeping

import androidx.fragment.app.Fragment
import ru.psbank.bookkeeping.core.BookkeepingStarter
import ru.psbank.bookkeeping.ui.BookkeepingFragment
import javax.inject.Inject

internal class BookkeepingStarterImpl @Inject constructor() : BookkeepingStarter {
    override fun createBookkeepingFragment(): Fragment = BookkeepingFragment.newInstance()
}

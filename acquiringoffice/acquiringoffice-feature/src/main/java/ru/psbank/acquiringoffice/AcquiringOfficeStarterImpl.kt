package ru.psbank.acquiringoffice

import androidx.fragment.app.Fragment
import ru.psbank.acquiringoffice.core.AcquiringOfficeStarter
import ru.psbank.acquiringoffice.ui.TerminalFragment
import javax.inject.Inject

internal class AcquiringOfficeStarterImpl @Inject constructor() : AcquiringOfficeStarter {
    override fun createTerminalFragment(): Fragment = TerminalFragment.newInstance()
}

package ru.psbank.acquiring

import androidx.fragment.app.Fragment
import ru.psbank.acquiring.core.AcquiringStarter
import ru.psbank.acquiring.ui.AcquiringFragment
import javax.inject.Inject

internal class AcquiringStarterImpl @Inject constructor() : AcquiringStarter {
    override fun createAcquiringFragment(): Fragment = AcquiringFragment.newInstance()
}

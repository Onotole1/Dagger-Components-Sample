package ru.psbank.mainscreen.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import ru.psbank.acquiring.core.AcquiringStarter
import ru.psbank.acquiringoffice.core.AcquiringOfficeStarter
import ru.psbank.acquiringoffice.core.CurrencyOperationsStarter
import ru.psbank.bookkeeping.core.BookkeepingStarter
import ru.psbank.mainscreen.R
import ru.psbank.mainscreen.core.R as mainscreencoreR
import ru.psbank.mainscreen.databinding.FragmentMainscreenBinding
import javax.inject.Inject

@AndroidEntryPoint
internal class MainScreenFragment : Fragment(R.layout.fragment_mainscreen) {

    @Inject
    lateinit var acquiringOfficeStarter: AcquiringOfficeStarter

    @Inject
    lateinit var acquiringStarter: AcquiringStarter

    @Inject
    lateinit var bookkeepingStarter: BookkeepingStarter

    @Inject
    lateinit var currencyOperationsStarter: CurrencyOperationsStarter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentMainscreenBinding.bind(view)
        binding.acquiring.setOnClickListener {
            parentFragmentManager.commit {
                replace(mainscreencoreR.id.container, acquiringStarter.createAcquiringFragment())
                addToBackStack(null)
            }
        }

        binding.acquiringOffice.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    mainscreencoreR.id.container,
                    acquiringOfficeStarter.createTerminalFragment(),
                )
                addToBackStack(null)
            }
        }

        binding.bookKeeping.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    mainscreencoreR.id.container,
                    bookkeepingStarter.createBookkeepingFragment(),
                )
                addToBackStack(null)
            }
        }

        binding.currencyOperations.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    mainscreencoreR.id.container,
                    currencyOperationsStarter.createCurrencyOperationsFragment(),
                )
                addToBackStack(null)
            }
        }
    }
}

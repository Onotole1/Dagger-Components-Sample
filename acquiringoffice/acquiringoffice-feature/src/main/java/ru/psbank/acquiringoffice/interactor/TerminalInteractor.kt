package ru.psbank.acquiringoffice.interactor

import ru.psbank.acquiringoffice.repository.Terminal
import ru.psbank.acquiringoffice.usecase.GetTerminalsUseCase
import javax.inject.Inject

internal class TerminalInteractor @Inject constructor(
    private val getTerminalsUseCase: GetTerminalsUseCase,
) {
    suspend fun loadTerminals(): Result<List<Terminal>> = getTerminalsUseCase()
}

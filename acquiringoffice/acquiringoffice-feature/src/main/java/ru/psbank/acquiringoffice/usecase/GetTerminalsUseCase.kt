package ru.psbank.acquiringoffice.usecase

import ru.psbank.acquiringoffice.repository.Terminal
import ru.psbank.acquiringoffice.repository.TerminalRepository
import javax.inject.Inject

internal class GetTerminalsUseCase @Inject constructor(private val repository: TerminalRepository) {
    suspend operator fun invoke(): Result<List<Terminal>> = runCatching {
        repository.fetchTerminals()
    }
}

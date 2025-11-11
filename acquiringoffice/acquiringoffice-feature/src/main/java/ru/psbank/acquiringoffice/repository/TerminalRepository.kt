package ru.psbank.acquiringoffice.repository

import kotlinx.coroutines.delay
import javax.inject.Inject

interface TerminalRepository {
    suspend fun fetchTerminals(): List<Terminal>
}

class FakeTerminalRepository @Inject constructor() : TerminalRepository {
    override suspend fun fetchTerminals(): List<Terminal> {
        delay(1000)
        return listOf(
            Terminal(
                id = "T001",
                serialNumber = "SN123456789",
                location = "Магазин №5, ул. Ленина, 10",
                status = TerminalStatus.ONLINE,
                lastActivity = "05.11.2025 16:20"
            ),
            Terminal(
                id = "T002",
                serialNumber = "SN987654321",
                location = "Кафе «Уют», пр. Мира, 25",
                status = TerminalStatus.OFFLINE,
                lastActivity = "04.11.2025 10:15"
            ),
            Terminal(
                id = "T003",
                serialNumber = "SN555111222",
                location = "Аптека «Здоровье», ул. Гагарина, 5",
                status = TerminalStatus.MAINTENANCE,
                lastActivity = "03.11.2025 14:40"
            )
        )
    }
}

enum class TerminalStatus {
    ONLINE, OFFLINE, MAINTENANCE
}

data class Terminal(
    val id: String,
    val serialNumber: String,
    val location: String,
    val status: TerminalStatus,
    val lastActivity: String? = null,
)
package ru.psbank.acquiringoffice.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.psbank.acquiringoffice.repository.Terminal
import ru.psbank.acquiringoffice.repository.TerminalStatus
import ru.psbank.acquiringoffice.R
import ru.psbank.acquiringoffice.databinding.ItemTerminalBinding

internal class TerminalAdapter : ListAdapter<Terminal, PaymentViewHolder>(
    TerminalDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentViewHolder = PaymentViewHolder(
        ItemTerminalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: PaymentViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}

internal class PaymentViewHolder(
    private val binding: ItemTerminalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(terminal: Terminal) {
        with(binding) {
            tvTerminalId.text = "Терминал ${terminal.id}"
            tvSerialNumber.text = terminal.serialNumber
            tvLocation.text = terminal.location
            tvLastActivity.text = if (terminal.lastActivity != null) {
                "Последнее действие: ${terminal.lastActivity}"
            } else {
                ""
            }

            when (terminal.status) {
                TerminalStatus.ONLINE -> {
                    ivTerminalStatus.setImageResource(R.drawable.ic_online)
                    ivTerminalStatus.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(root.context, R.color.colorTerminalOnline)
                    )
                }

                TerminalStatus.OFFLINE -> {
                    ivTerminalStatus.setImageResource(R.drawable.ic_offline)
                    ivTerminalStatus.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(root.context, R.color.colorTerminalOffline)
                    )
                }

                TerminalStatus.MAINTENANCE -> {
                    ivTerminalStatus.setImageResource(R.drawable.ic_maintenance)
                    ivTerminalStatus.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(root.context, R.color.colorTerminalMaintenance)
                    )
                }
            }
        }
    }
}

internal class TerminalDiffCallback : DiffUtil.ItemCallback<Terminal>() {
    override fun areItemsTheSame(
        oldItem: Terminal,
        newItem: Terminal
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Terminal,
        newItem: Terminal
    ): Boolean = oldItem == newItem
}
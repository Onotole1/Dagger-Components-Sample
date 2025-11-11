package ru.psbank.currencyoperations.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.psbank.currencyoperations.R
import ru.psbank.currencyoperations.databinding.ItemCurrencyBinding
import ru.psbank.currencyoperations.repository.CurrencyOperation
import ru.psbank.currencyoperations.repository.OperationStatus
import ru.psbank.currencyoperations.repository.OperationType

internal class CurrencyOperationsAdapter :
    ListAdapter<CurrencyOperation, CurrencyOperationViewHolder>(
        CurrencyOperationDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyOperationViewHolder = CurrencyOperationViewHolder(
        ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: CurrencyOperationViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}

class CurrencyOperationViewHolder(
    private val binding: ItemCurrencyBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(operation: CurrencyOperation) {
        // 1. Иконка типа операции
        binding.ivOperationType.setImageResource(
            when (operation.type) {
                OperationType.BUY -> R.drawable.ic_buy
                OperationType.SELL -> R.drawable.ic_sell
                OperationType.CONVERT -> R.drawable.ic_convert
            }
        )
        binding.ivOperationType.imageTintList = ColorStateList.valueOf(
            when (operation.type) {
                OperationType.BUY -> ContextCompat.getColor(itemView.context, R.color.color_buy)
                OperationType.SELL -> ContextCompat.getColor(itemView.context, R.color.color_sell)
                OperationType.CONVERT -> ContextCompat.getColor(
                    itemView.context,
                    R.color.color_convert
                )
            }
        )

        // 2. Направление конвертации
        binding.tvDirection.text = "${operation.fromCurrency} → ${operation.toCurrency}"

        // 3. Статус операции
        binding.tvStatus.text = when (operation.status) {
            OperationStatus.PENDING -> "В обработке"
            OperationStatus.COMPLETED -> "Выполнено"
            OperationStatus.FAILED -> "Ошибка"
        }
        binding.tvStatus.setTextColor(
            when (operation.status) {
                OperationStatus.PENDING -> ContextCompat.getColor(
                    itemView.context,
                    R.color.color_pending
                )

                OperationStatus.COMPLETED -> ContextCompat.getColor(
                    itemView.context,
                    R.color.color_completed
                )

                OperationStatus.FAILED -> ContextCompat.getColor(
                    itemView.context,
                    R.color.color_failed
                )
            }
        )

        binding.tvAmountFrom.text =
            String.format("%.2f %s", operation.amountFrom, operation.fromCurrency)

        binding.tvAmountTo.text = String.format(
            "%.2f %s",
            operation.amountTo,
            operation.toCurrency
        )

        binding.tvRate.text = "Курс: ${String.format("%.4f", operation.rate)}"


        binding.tvFee.text =
            "Комиссия: ${String.format("%.2f", operation.fee)} ${operation.toCurrency}"

        binding.tvTimestamp.text = operation.timestamp
    }
}

internal class CurrencyOperationDiffCallback : DiffUtil.ItemCallback<CurrencyOperation>() {
    override fun areItemsTheSame(
        oldItem: CurrencyOperation,
        newItem: CurrencyOperation
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CurrencyOperation,
        newItem: CurrencyOperation
    ): Boolean = oldItem == newItem
}

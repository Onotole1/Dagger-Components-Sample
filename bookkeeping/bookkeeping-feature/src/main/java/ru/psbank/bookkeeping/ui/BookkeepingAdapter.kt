package ru.psbank.bookkeeping.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.psbank.bookkeeping.databinding.ItemTransactionBinding
import ru.psbank.bookkeeping.repository.Transaction

internal class BookkeepingAdapter : ListAdapter<Transaction, TransactionViewHolder>(
    TransactionDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder = TransactionViewHolder(
        ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}

internal class TransactionViewHolder(
    private val binding: ItemTransactionBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Transaction) {
        binding.tvDate.text = item.date
        binding.tvAmount.text = item.amount.toString()
        binding.tvDescription.text = item.description
    }
}

internal class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(
        oldItem: Transaction,
        newItem: Transaction
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Transaction,
        newItem: Transaction
    ): Boolean = oldItem == newItem
}
package ru.psbank.acquiring.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.psbank.acquiring.repository.Payment
import ru.psbank.acquiring.repository.PaymentStatus
import ru.psbank.acquiring.R
import ru.psbank.acquiring.databinding.ItemPaymentBinding

internal class AcquiringAdapter : ListAdapter<Payment, PaymentViewHolder>(
    PaymentDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentViewHolder = PaymentViewHolder(
        ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: PaymentViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}

internal class PaymentViewHolder(
    private val binding: ItemPaymentBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Payment) {
        binding.tvMerchant.text = item.merchant
        binding.tvAmount.text = item.amount.toString()
        binding.tvTimestamp.text = item.timestamp
        binding.ivPaymentStatus.setImageResource(
            when (item.status) {
                PaymentStatus.SUCCESS -> R.drawable.ic_check_circle
                PaymentStatus.FAILED -> R.drawable.ic_cancel
                PaymentStatus.PENDING -> R.drawable.ic_pending
            }
        )
        val context = binding.root.context
        binding.ivPaymentStatus.imageTintList = when (item.status) {
            PaymentStatus.SUCCESS -> {
                ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.colorPaymentSuccess)
                )
            }
            PaymentStatus.FAILED -> {
                ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.colorPaymentFailed)
                )
            }
            PaymentStatus.PENDING -> {
                ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.colorPaymentPending)
                )
            }
        }
    }
}

internal class PaymentDiffCallback : DiffUtil.ItemCallback<Payment>() {
    override fun areItemsTheSame(
        oldItem: Payment,
        newItem: Payment
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Payment,
        newItem: Payment
    ): Boolean = oldItem == newItem
}
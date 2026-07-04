package com.vspace.ui.adapter

import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vspace.databinding.WidgetAppCardBinding

data class AppItem(
    val appName: String,
    val packageName: String,
    val appInfo: ApplicationInfo
)

class AppAdapter(
    private val items: List<AppItem>,
    private val onItemClick: (AppItem) -> Unit
) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    inner class AppViewHolder(private val binding: WidgetAppCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AppItem) {
            binding.apply {
                tvAppName.text = item.appName
                tvPackageName.text = item.packageName
                root.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding = WidgetAppCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

package com.github.naz013.todoappconcept.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.naz013.todoappconcept.R
import com.github.naz013.todoappconcept.databinding.ListItemSelectorAllBinding
import com.github.naz013.todoappconcept.databinding.ListItemSelectorTodayBinding

class DateSelectorView : RecyclerView {

    private val items = mutableListOf<DateItem<*>>()
    private val innerAdapter = Adapter()
    var onDateSelectedListener: OnDateSelectedListener? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(context, attributeSet, defStyle) {
        initView()
    }

    fun showDates(list: List<DateItem<*>>) {
        if (list.isEmpty()) {
            throw IllegalArgumentException("Cannot be empty")
        }
        if (list.size < 3) {
            throw IllegalArgumentException("List cannot contain less than 3 items")
        }
        if (list.size > 5) {
            throw IllegalArgumentException("List cannot contain more than 5 items")
        }
        this.items.clear()
        this.items.addAll(list)
        this.innerAdapter.notifyDataSetChanged()
        findSelected().let { position ->
            if (position != -1) {
                onDateSelectedListener?.onDateSelected(position, items[position])
            }
        }
    }

    private fun findSelected(): Int {
        items.forEachIndexed { index, dateItem ->
            if (dateItem.isSelected) return index
        }
        return -1
    }

    private fun initView() {
        layoutManager = GridLayoutManager(context, 5, VERTICAL, false)
        adapter = innerAdapter

        demoSelector()
    }

    private fun demoSelector() {
        val list = mutableListOf<DateItem<*>>()
        list.add(DateItem<String>(5, 0, 2, "Jan", "today", true))
        list.add(DateItem<String>(6, 0, 3, "Jan", "", false))
        list.add(DateItem<String>(7, 0, 0, "Jan", "", false))
        list.add(DateItem<String>(8, 0, 5, "Jan", "", false))
        list.add(DateItem<String>(-1, R.drawable.ic_calendar_waiting, 10, "", "", false))
        showDates(list)
    }

    private fun onItemClick(position: Int) {
        if (items[position].isSelected) {
            onDateSelectedListener?.onDateSelected(position, items[position])
        } else {
            items.forEach { it.isSelected = false }
            items[position].isSelected = true
            innerAdapter.notifyDataSetChanged()
            onDateSelectedListener?.onDateSelected(position, items[position])
        }
    }

    fun updateCounter(count: Int) {
        findSelected().let { position ->
            if (position != -1) {
                items[position].count = count
                innerAdapter.notifyItemChanged(position)
            }
        }
    }

    private inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return if (viewType == 1) {
                CalendarHolder(parent)
            } else {
                DayHolder(parent)
            }
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            when (holder) {
                is CalendarHolder -> {
                    holder.bind(items[position])
                }
                is DayHolder -> {
                    holder.bind(items[position])
                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            return if (items[position].day == -1) {
                1
            } else {
                0
            }
        }
    }

    private inner class CalendarHolder(parent: ViewGroup) : ViewHolder(
        ListItemSelectorAllBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        private val binding: ListItemSelectorAllBinding = DataBindingUtil.bind(itemView)!!
        init {
            binding.root.setOnClickListener { onItemClick(adapterPosition) }
        }
        fun bind(item: DateItem<*>) {
            val context = binding.cardView.context
            if (item.isSelected) {
                binding.cardView.setBackgroundResource(R.drawable.date_selector_card_bg_selected)
                binding.iconView.imageTintList = ContextCompat.getColorStateList(context, R.color.colorCardBackground)
            } else {
                binding.cardView.setBackgroundResource(R.drawable.date_selector_card_bg)
                binding.iconView.imageTintList = ContextCompat.getColorStateList(context, R.color.colorSecondaryVariant)
            }
            binding.counterView.text = "${item.count}"
        }
    }

    private inner class DayHolder(parent: ViewGroup) : ViewHolder(
        ListItemSelectorTodayBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
    ) {
        private val binding: ListItemSelectorTodayBinding = DataBindingUtil.bind(itemView)!!
        init {
            binding.root.setOnClickListener { onItemClick(adapterPosition) }
        }
        fun bind(item: DateItem<*>) {
            val context = binding.cardView.context
            if (item.isSelected) {
                binding.cardView.setBackgroundResource(R.drawable.date_selector_card_bg_selected)
                binding.dayView.setTextColor(ContextCompat.getColor(context, R.color.colorCardBackground))
                binding.todayView.setTextColor(ContextCompat.getColor(context, R.color.colorCardBackground))
            } else {
                binding.cardView.setBackgroundResource(R.drawable.date_selector_card_bg)
                binding.dayView.setTextColor(ContextCompat.getColor(context, R.color.colorSecondaryVariant))
                binding.todayView.setTextColor(ContextCompat.getColor(context, R.color.colorSecondaryVariant))
            }
            binding.counterView.text = "${item.count}"
            binding.dayView.text = "${item.day}"
            binding.monthView.text = item.subtitle
            if (item.extraText.isEmpty()) {
                binding.todayView.visibility = View.GONE
            } else {
                binding.todayView.visibility = View.VISIBLE
            }
        }
    }

    interface OnDateSelectedListener {
        fun onDateSelected(position: Int, dateItem: DateItem<*>)
    }

    data class DateItem<P> (
        var day: Int = -1,
        @DrawableRes var icon: Int = 0,
        var count: Int = 0,
        var subtitle: String = "",
        var extraText: String = "",
        var isSelected: Boolean = false,
        var payload: P? = null
    )
}
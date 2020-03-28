package com.github.naz013.todoappconcept.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.naz013.todoappconcept.R
import com.github.naz013.todoappconcept.data.EventState
import com.github.naz013.todoappconcept.data.ListEvent
import com.github.naz013.todoappconcept.databinding.ListItemEventBinding
import com.github.naz013.todoappconcept.utils.hide
import com.github.naz013.todoappconcept.utils.show
import com.github.naz013.todoappconcept.utils.toDate
import com.github.naz013.todoappconcept.utils.toUserReadableTime

typealias ItemClickListener = (listEvent: ListEvent, position: Int) -> Unit

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.Holder>() {

    var data: List<ListEvent> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var itemClickListener: ItemClickListener? = null

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], prevItem(position))
    }

    private fun prevItem(position: Int): ListEvent? = try {
        data[position - 1]
    } catch (e: Exception) {
        null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ListItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = data.size

    inner class Holder(private val binding: ListItemEventBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        init {
            binding.checkView.setOnClickListener {
                itemClickListener?.invoke(data[adapterPosition], adapterPosition)
            }
        }

        fun bind(listEvent: ListEvent, prevItem: ListEvent?) {
            if (listEvent.count == ListEvent.NO_HEADER_COUNTER) {
                binding.headerView.hide()
            } else {
                binding.headerView.show()
            }
            binding.counterView.text = "${listEvent.count} Task"
            binding.groupNameView.text = listEvent.groupName
            with(listEvent.event) {
                binding.checkView.setImageResource(getIcon(this.state == EventState.COMPLETED))
                binding.summaryView.text = this.summary
                binding.timeView.text = this.dueTime?.toDate()?.toUserReadableTime() ?: ""
                if (this.state == EventState.COMPLETED) {
                    binding.summaryView.crossText()
                    binding.timeView.crossText()
                } else {
                    binding.summaryView.plainText()
                    binding.timeView.plainText()
                }
            }
        }

        private fun getIcon(b: Boolean) = if (b) {
            R.drawable.ic_calendar_done
        } else {
            R.drawable.ic_calendar_unchecked
        }
    }
}
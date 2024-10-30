package com.example.gmail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Email(
    val sender: String,
    val preview: String,
    val time: String,
    val isStarred: Boolean,
    val avatarColor: Int
)

class EmailAdapter(private val emails: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView = itemView.findViewById(R.id.senderTextView)
        val previewTextView: TextView = itemView.findViewById(R.id.previewTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val starImageView: ImageView = itemView.findViewById(R.id.starImageView)
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emails[position]
        holder.senderTextView.text = email.sender
        holder.previewTextView.text = email.preview
        holder.timeTextView.text = email.time
        holder.avatarImageView.setBackgroundColor(email.avatarColor)

        if (email.isStarred) {
            holder.starImageView.setImageResource(R.drawable.ic_star_filled)
        } else {
            holder.starImageView.setImageResource(R.drawable.ic_star_outline)
        }
    }

    override fun getItemCount(): Int {
        return emails.size
    }
}

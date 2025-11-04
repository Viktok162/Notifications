package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.quantityWritingRule
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

interface OnInteractorListener{
    fun onLike(post:Post)
    fun onRemove(post:Post)
    fun onEdit(post:Post)
    fun onShare(post: Post)
    fun onEye(post: Post)
    fun onPlayVideo(post: Post)
    fun onPostClick(post: Post)
}

class PostAdapter(
    private val onInteractorListener: OnInteractorListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractorListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractorListener: OnInteractorListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) = with(binding){
        author.text = post.author
        content.text = post.content
        published.text = post.published

        heart.apply{
            isChecked = post.likeByMe
            text = quantityWritingRule(post.likes)
        }

        heart.setOnClickListener {
            onInteractorListener.onLike(post)
        }

        share.apply {
            share.setOnClickListener {
                onInteractorListener.onShare(post)
            }
            text = quantityWritingRule(post.shares)
        }

        eye.apply {
            eye.setOnClickListener {
                onInteractorListener.onEye(post)
            }
            text = quantityWritingRule(post.looks)
        }

        menu.setOnClickListener {
            PopupMenu(it.context, it).apply{
                inflate(R.menu.post_options)
                setOnMenuItemClickListener { item ->
                    when(item.itemId){
                        R.id.remove -> {
                            onInteractorListener.onRemove(post)
                            true
                        }
                        R.id.edit -> {
                            onInteractorListener.onEdit(post)
                            true
                        }
                        else -> false
                    }
                }
            }.show()
        }

        if (!post.video.isNullOrBlank()) {
            binding.videoContainer.visibility = View.VISIBLE
            binding.videoMsc.visibility = View.VISIBLE
            binding.buttonPlay.visibility = View.VISIBLE

            val clickListener = View.OnClickListener {
                onInteractorListener.onPlayVideo(post)
            }
            binding.videoContainer.setOnClickListener(clickListener)
            binding.videoMsc.setOnClickListener(clickListener)
            binding.buttonPlay.setOnClickListener(clickListener)

        } else {
            binding.videoContainer.visibility = View.GONE
            binding.videoMsc.visibility = View.GONE
            binding.buttonPlay.visibility = View.GONE
        }

        content.setOnClickListener {
            onInteractorListener.onPostClick(post)
        }


    }
}

object PostDiffCallBack: DiffUtil.ItemCallback<Post>(){

    override fun areItemsTheSame(
        oldItem: Post,
        newItem: Post
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Post,
        newItem: Post
    ): Boolean {
        return oldItem == newItem
    }
}

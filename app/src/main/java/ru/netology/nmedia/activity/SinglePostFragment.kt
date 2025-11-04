package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nmedia.R
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController

import ru.netology.nmedia.databinding.FragmentSinglePostBinding
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel
import kotlin.getValue

class SinglePostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSinglePostBinding.inflate(
            inflater,
            container,
            false
        )
        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
        val postId = arguments?.getLong("postId") ?: 0L
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val post = posts.find { it.id == postId }
            post?.let {
                with(binding.post) {
                    author.text = it.author
                    content.text = it.content
                    published.text = it.published

                    heart.apply {
                        isChecked = it.likeByMe
                        text = quantityWritingRule(it.likes)
                        setOnClickListener {
                            viewModel.like(postId)
                        }
                    }


                    share.apply {
                        text = quantityWritingRule(it.shares)
                        setOnClickListener {
                            val intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, post.content)
                                type = "text/plain"
                            }
                            val intent2 =
                                Intent.createChooser(intent, getString(R.string.chooser_share_post))
                            startActivity(intent2)
                            viewModel.share(postId)

                        }
                    }

                    eye.apply {
                        text = quantityWritingRule(it.looks)
                        setOnClickListener {
                            viewModel.look(postId)
                        }
                    }

                    menu.setOnClickListener { menuView ->
                        PopupMenu(menuView.context, menuView).apply {
                            inflate(R.menu.post_options)
                            setOnMenuItemClickListener { item ->
                                when (item.itemId) {
                                    R.id.remove -> {
                                        viewModel.removeById(postId)
                                        findNavController().popBackStack()
                                        true
                                    }

                                    R.id.edit -> {
                                        viewModel.edit(post)
                                        findNavController().navigate(
                                            R.id.action_singlePostFragment_to_newPostFragment,
                                            Bundle().apply {
                                                textArgs = post.content
                                            }
                                        )
                                        true
                                    }
                                    else -> false
                                }
                            }
                        }.show()
                    }
                }
            }
        }
            return binding.root
    }
    companion object{
        var Bundle.textArgs by StringArg
    }
}





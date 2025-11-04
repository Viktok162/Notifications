package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.AcEditPostBinding


class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AcEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val content = intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""

        binding.editPost.setText(content)
        binding.editPost.requestFocus()

        binding.confirm.setOnClickListener {
            val newContent = binding.editPost.text.toString()
            if (newContent.isBlank()) {
                binding.editPost.error = getString(R.string.error_empty_post)
                return@setOnClickListener
            }

            val resultIntent = Intent().putExtra(Intent.EXTRA_TEXT, newContent)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}


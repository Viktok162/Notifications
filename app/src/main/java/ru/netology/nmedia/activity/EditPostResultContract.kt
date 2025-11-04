package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class EditPostResultContract: ActivityResultContract<String, String?>(){

    override fun createIntent(
        context: Context,
        input: String
    ): Intent {
        return Intent(context, EditPostActivity::class.java).putExtra(Intent.EXTRA_TEXT, input)
    }

    override fun parseResult(
        resultCode: Int,
        intent: Intent?
    ): String? {
        if (resultCode != Activity.RESULT_OK) return null
        return intent?.getStringExtra(Intent.EXTRA_TEXT)
    }

}
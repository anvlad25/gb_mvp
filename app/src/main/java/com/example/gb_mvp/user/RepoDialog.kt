package com.example.gb_mvp.user

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.gb_mvp.data.UserRepos

class RepoDialog(private val repo: UserRepos) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(repo.name)
                .setMessage(
                    "forks: ${repo.forks}\n" +
                    "watchers: ${repo.watchers}"
                )
                .setPositiveButton("ОК") { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
package com.kabos.firebasesample

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.kabos.firebasesample.databinding.DialogAddMemoBinding

class AddMemoDialog: DialogFragment() {
    private lateinit var binding: DialogAddMemoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddMemoBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onStart() {
        super.onStart()

        binding.apply {

        }
    }
}

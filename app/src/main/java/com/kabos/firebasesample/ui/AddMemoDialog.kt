package com.kabos.firebasesample.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kabos.firebasesample.databinding.DialogAddMemoBinding
import com.kabos.firebasesample.model.MemoItem
import com.kabos.firebasesample.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMemoDialog: DialogFragment() {
    private lateinit var binding: DialogAddMemoBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddMemoBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setTitle("Create Memo")
            .create()
    }

    override fun onStart() {
        super.onStart()

        binding.apply {
            btnDialogSave.setOnClickListener {
                val memo = MemoItem(userId = "shuhei",
                    title = etDialogTitle.text.toString())
                viewModel.addMemo(memo)
                findNavController().popBackStack()
            }

            btnDialogCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}

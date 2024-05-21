package com.example.ankilightapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ankilightapp.databinding.FragmentEditBinding
import com.example.ankilightapp.viewmodel.CardEditViewModel

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private val viewModel: CardEditViewModel by viewModels()
    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false).apply {
            viewModel = this@EditFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.loadCard(args.cardId)

        binding.saveButton.setOnClickListener {
            viewModel.saveCard(
                args.cardId,
                binding.wordEditText.text.toString(),
                binding.translationEditText.text.toString(),
                binding.exampleEditText.text.toString()
            )
            findNavController().navigateUp()
        }
        return binding.root
    }
}

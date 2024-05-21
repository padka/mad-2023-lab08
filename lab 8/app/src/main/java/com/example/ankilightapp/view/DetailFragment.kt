package com.example.ankilightapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ankilightapp.databinding.FragmentDetailBinding
import com.example.ankilightapp.viewmodel.CardDetailViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: CardDetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            viewModel = this@DetailFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.loadCard(args.cardId)
        return binding.root
    }
}

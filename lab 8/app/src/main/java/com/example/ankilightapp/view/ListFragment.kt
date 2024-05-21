package com.example.ankilightapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ankilightapp.databinding.FragmentListBinding
import com.example.ankilightapp.view.adapter.CardAdapter
import com.example.ankilightapp.viewmodel.CardListViewModel

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: CardListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false).apply {
            viewModel = this@ListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val adapter = CardAdapter { card ->
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(card.id)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter

        viewModel.allCards.observe(viewLifecycleOwner) { cards ->
            cards?.let {
                adapter.submitList(it)
            }
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToEditFragment(0))
        }

        return binding.root
    }
}

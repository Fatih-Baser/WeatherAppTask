package com.edushare.weatherapptask.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.edushare.weatherapptask.R
import com.edushare.weatherapptask.SearchViewModel
import com.edushare.weatherapptask.appComponent
import com.edushare.weatherapptask.databinding.FragmentSearchBinding
import com.edushare.weatherapptask.isConnected
import com.edushare.weatherapptask.models.State
import com.edushare.weatherapptask.models.Weather
import com.jakewharton.rxbinding.widget.RxTextView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private var isShown = false

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeWeather()
        setupAutocompleteField()
        binding.button.setOnClickListener { onButtonPressed() }
    }

    private fun setupAutocompleteField() {
        observePreviousQueries()
        RxTextView.textChanges(binding.searchField)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel.checkQuery(it.toString())
            }
    }

    private fun observePreviousQueries() {
        val field = binding.searchField
        viewModel.previousQueriesLiveData.observe(viewLifecycleOwner) {
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                it
            ).also { adapter ->
                field.setAdapter(adapter)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onButtonPressed() {
        isShown = false
        val query = binding.searchField.text.toString()
        viewModel.searchWeather(query.trim(), requireContext().isConnected)
    }


    private fun observeWeather() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.Loading -> {
                    showLoading()
                }
                is State.Success -> {
                    val weather = state.data
                    showWeatherDetails(weather)
                }
                is State.Error -> {
                    showError(state.message)
                }
            }
        }
    }

    private fun showWeatherDetails(weather: Weather) {
        binding.progress.visibility = View.GONE
        if (!isShown) {
            val direction = SearchFragmentDirections.actionSearchFragmentToDetailFragment(weather)
            findNavController().navigate(direction)
            isShown = true
        }
    }

    private fun showError(message: String) {
        binding.progress.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

}
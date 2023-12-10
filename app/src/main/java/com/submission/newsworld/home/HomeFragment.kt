package com.submission.newsworld.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.newsworld.core.data.Resource
import com.submission.newsworld.databinding.FragmentHomeBinding
import com.submission.newsworld.detail.DetailNewsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {


    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val newsAdapter = com.submission.newsworld.core.ui.NewsAdapter()
            newsAdapter.onItemClick = { selectedData->
                val intent = Intent(activity, DetailNewsActivity::class.java)
                intent.putExtra(DetailNewsActivity.EXTRA_DATA,selectedData)
                startActivity(intent)
            }


            homeViewModel.news.observe(viewLifecycleOwner){ news ->
                if (news != null){
                    when(news){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            newsAdapter.setData(news.data)
                        }
                        is Resource.Error ->{
                            Toast.makeText(requireActivity(),"Erorr", Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }

            }

            with(binding.rvNews){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
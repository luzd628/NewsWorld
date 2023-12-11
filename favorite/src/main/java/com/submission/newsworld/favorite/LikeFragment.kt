package com.submission.newsworld.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.newsworld.detail.DetailNewsActivity
import com.submission.newsworld.favorite.databinding.FragmentLikeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class LikeFragment : Fragment() {

    private val likeViewModel:LikeViewModel by viewModel()

    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favModule)

        if (activity != null){
            val newsAdapter = com.submission.newsworld.core.ui.NewsAdapter()
            newsAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailNewsActivity::class.java)
                intent.putExtra(DetailNewsActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }


            likeViewModel.favoriteNews.observe(viewLifecycleOwner) { dataNews ->
                newsAdapter.setData(dataNews)
                binding.viewEmpty.root.visibility =
                    if (dataNews.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvNews) {
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
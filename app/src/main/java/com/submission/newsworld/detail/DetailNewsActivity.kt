package com.submission.newsworld.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.submission.newsworld.R
import com.submission.newsworld.core.domain.model.News
import com.submission.newsworld.databinding.ActivityDetailNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNewsActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private val detailNewsViewModel: DetailNewsViewModel by viewModel()

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailNews = intent.getParcelableExtra<News>(EXTRA_DATA)
        showDetailNews(detailNews)

    }

    private fun showDetailNews(detailNews: News?){
        detailNews?.let {
            supportActionBar?.title = "Detail Berita"
            binding.content.tvJudul.text = detailNews.title
            binding.content.tvDetailDescription.text = detailNews.description
            binding.content.tvLink.text = detailNews.link
            Glide.with(this@DetailNewsActivity)
                .load(detailNews.thumbnail)
                .into(binding.ivDetailImage)

            var statusFavorite = detailNews.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailNewsViewModel.setFavoriteNews(detailNews, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean){
        if (statusFavorite){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_favorite))
        }else{
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_not_favorite))
        }
    }

}
package com.frogobox.generalframework.mvvm.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.frogobox.generalframework.R
import com.frogobox.generalframework.core.BaseActivity
import com.frogobox.generalframework.databinding.ActivityMainBinding
import com.frogobox.generalframework.mvvm.detail.DetailActivity
import com.frogobox.generalframework.model.Article
import com.frogobox.recycler.core.IFrogoViewAdapter
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(baseLayoutInflater())
        setContentView(activityMainBinding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        mainViewModel.apply {

            usingChuck()
            getTopHeadline()
            topHeadlineLive.observe(this@MainActivity, {
                it.articles?.let { it1 -> setupRvNews(it1) }
            })

            eventShowProgress.observe(this@MainActivity, {
                setupEventProgressView(activityMainBinding.progressView, it)
            })

        }

    }

    private fun setupRvNews(data: List<Article>) {

        val newsAdapter = object : IFrogoViewAdapter<Article> {
            override fun onItemClicked(data: Article) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                val extraData = Gson().toJson(data)
                intent.putExtra("EXTRA_DATA_ARTICLE", extraData)
                startActivity(intent)
            }

            override fun onItemLongClicked(data: Article) {

            }

            override fun setupInitComponent(view: View, data: Article) {
                view.findViewById<TextView>(R.id.tv_title).text = data.title
                view.findViewById<TextView>(R.id.tv_description).text = data.description
                view.findViewById<TextView>(R.id.tv_published).text = data.publishedAt
                Glide.with(view.context).load(data.urlToImage).into(view.findViewById(R.id.iv_url))
            }
        }

        activityMainBinding.rvNews
            .injector<Article>()
            .addData(data)
            .addCustomView(R.layout.list_news_article_vertical)
            .addEmptyView(null)
            .addCallback(newsAdapter)
            .createLayoutLinearVertical(false)
            .build()
    }

}

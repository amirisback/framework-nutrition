package com.frogobox.nutritionapp.mvvm.nutrition.article

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.frogobox.nutritionapp.R
import com.frogobox.nutritionapp.core.BaseActivity
import com.frogobox.nutritionapp.databinding.ActivityArticleBinding
import com.frogobox.nutritionapp.model.Menu
import com.frogobox.nutritionapp.util.Constant
import com.frogobox.nutritionapp.util.Constant.Extra.EXTRA_ARTICLE
import com.frogobox.nutritionapp.util.Constant.Extra.EXTRA_ARTICLE_DETAIL_TITLE
import com.frogobox.nutritionapp.util.Constant.Extra.EXTRA_ARTICLE_TITLE
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_CALORIE_ARTICLE
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_CALORIE_ARTICLE_DETAIL
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_NUTRITION_ARTICLE
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_NUTRITION_ARTICLE_DETAIL
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_VITAMIN_ARTICLE
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_VITAMIN_ARTICLE_A
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_VITAMIN_ARTICLE_C
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_VITAMIN_ARTICLE_DETAIL
import com.frogobox.nutritionapp.util.Constant.TitleActivity.ACTIVITY_VITAMIN_ARTICLE_E
import com.frogobox.nutritionframework.recycler.core.INutriViewAdapter

class ArticleActivity : BaseActivity<ActivityArticleBinding>() {

    override fun setupViewBinding(): ActivityArticleBinding {
        return ActivityArticleBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity(Constant.TitleActivity.ACTIVITY_ARTICLE)
        binding.apply {
            mainNutriRv.injector<Menu>()
                .addCustomView(R.layout.nutri_rv_list_type_1)
                .addData(data())
                .addCallback(object : INutriViewAdapter<Menu> {
                    override fun onItemClicked(data: Menu) {
                        startActivity(data.intent)
                    }

                    override fun onItemLongClicked(data: Menu) {}
                    override fun setupInitComponent(view: View, data: Menu) {
                        view.findViewById<TextView>(R.id.nutri_rv_list_type_1_tv_title).text =
                            data.name
                    }
                })
                .createLayoutLinearVertical(false)
                .build()
        }
    }

    private fun data(): MutableList<Menu> {
        val data = mutableListOf<Menu>()
        data.add(
            Menu(
                ACTIVITY_NUTRITION_ARTICLE,
                intentArticle(
                    ACTIVITY_NUTRITION_ARTICLE,
                    ACTIVITY_NUTRITION_ARTICLE_DETAIL,
                    "Nutrisi"
                )
            )
        )
        data.add(
            Menu(
                ACTIVITY_VITAMIN_ARTICLE,
                intentArticle(
                    ACTIVITY_VITAMIN_ARTICLE,
                    ACTIVITY_VITAMIN_ARTICLE_DETAIL,
                    "Vitamin"
                )
            )
        )
        data.add(
            Menu(
                ACTIVITY_VITAMIN_ARTICLE_A,
                intentArticle(
                    ACTIVITY_VITAMIN_ARTICLE_A,
                    ACTIVITY_VITAMIN_ARTICLE_DETAIL,
                    "Vitamin A"
                )
            )
        )
        data.add(
            Menu(
                ACTIVITY_VITAMIN_ARTICLE_C,
                intentArticle(
                    ACTIVITY_VITAMIN_ARTICLE_C,
                    ACTIVITY_VITAMIN_ARTICLE_DETAIL,
                    "Vitamin C"
                )
            )
        )
        data.add(
            Menu(
                ACTIVITY_VITAMIN_ARTICLE_E,
                intentArticle(
                    ACTIVITY_VITAMIN_ARTICLE_E,
                    ACTIVITY_VITAMIN_ARTICLE_DETAIL,
                    "Vitamin E"
                )
            )
        )
        data.add(
            Menu(
                ACTIVITY_CALORIE_ARTICLE,
                intentArticle(
                    ACTIVITY_CALORIE_ARTICLE,
                    ACTIVITY_CALORIE_ARTICLE_DETAIL,
                    "Kalori"
                )
            )
        )
        return data
    }

    private fun intentArticle(title: String, titleDetail: String, query: String): Intent {
        return Intent(this, ArticleCategoryActivity::class.java)
            .putExtra(EXTRA_ARTICLE_TITLE, title)
            .putExtra(EXTRA_ARTICLE_DETAIL_TITLE, titleDetail)
            .putExtra(EXTRA_ARTICLE, query)
    }

}
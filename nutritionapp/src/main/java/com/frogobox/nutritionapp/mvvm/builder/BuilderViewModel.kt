package com.frogobox.nutritionapp.mvvm.builder

import android.app.Application
import com.frogobox.nutritionapp.source.DataRepository
import com.frogobox.nutritionframework.core.NutriViewModel
import com.frogobox.nutritionframework.util.NutriSingleLiveEvent


/*
 * Created by faisalamir on 07/12/21
 * NutritionFramework
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

class BuilderViewModel(
    private val context: Application,
    private val repository: DataRepository
) : NutriViewModel(context) {

    var prefSample = NutriSingleLiveEvent<String>()

    fun savePrefSample(key: String, value: String) {
        repository.savePrefSample(key, value)
    }

    fun getPrefSample(key: String) {
        prefSample.postValue(repository.getPrefSample(key))
    }

}
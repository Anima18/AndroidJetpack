/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package paging.android.example.com.pagingsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import paging.android.example.com.pagingsample.datasource.CheesesDataSourceFactory

/**
 * A simple ViewModel that provides a paged list of delicious Cheeses.
 */
class CheeseViewModel(app: Application) : AndroidViewModel(app) {
    val myPagingConfig = PagedList.Config.Builder()
            .setPageSize(30)
            .setPrefetchDistance(90)
            .setEnablePlaceholders(false)
            .build()
    /**
     * We use -ktx Kotlin extension functions here, otherwise you would use LivePagedListBuilder(),
     * and PagedList.Config.Builder()
     */
    //val allCheeses = dao.allCheesesByName().toLiveData(myPagingConfig)

    val allCheeses = CheesesDataSourceFactory().toLiveData(myPagingConfig)

}

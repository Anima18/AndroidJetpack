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
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.chris.androidjetpack.piging.Cheese
import com.chris.androidjetpack.piging.Listing
import paging.android.example.com.pagingsample.datasource.CheesesDataSourceFactory

/**
 * A simple ViewModel that provides a paged list of delicious Cheeses.
 */
class CheeseViewModel(app: Application) : AndroidViewModel(app) {
    /**
     * We use -ktx Kotlin extension functions here, otherwise you would use LivePagedListBuilder(),
     * and PagedList.Config.Builder()
     */
    //val allCheeses = dao.allCheesesByName().toLiveData(myPagingConfig)

    //val allCheeses = CheesesDataSourceFactory().toLiveData(myPagingConfig)

    /*fun allCheeses2(): LiveData<PagedList<Cheese>> {
        return CheesesDataSourceFactory().toLiveData(myPagingConfig);
    }*/

    fun allCheeses(): Listing<Cheese> {
        val sourceFactory = CheesesDataSourceFactory(super.getApplication())

        // We use toLiveData Kotlin ext. function here, you could also use LivePagedListBuilder
        val livePagedList = sourceFactory.toLiveData(
            // we use Config Kotlin ext. function here, could also use PagedList.Config.Builder
            config = Config(
                pageSize = 30,
                enablePlaceholders = false,
                initialLoadSizeHint = 30 * 2),
            // provide custom executor for network requests, otherwise it will default to
            // Arch Components' IO pool which is also used for disk access
            fetchExecutor = sourceFactory.getNetworkExecutor()
        )

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
            pagedList = livePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState
        )
    }



}

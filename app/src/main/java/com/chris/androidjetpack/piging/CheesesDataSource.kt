package paging.android.example.com.pagingsample.datasource

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.chris.androidjetpack.piging.Cheese
import com.chris.androidjetpack.piging.NetworkState
import java.util.concurrent.Executor
import com.ut.requsetmanager.callback.DataRequestCallback
import com.google.gson.reflect.TypeToken
import com.ut.requsetmanager.entity.ResponseStatus
import com.ut.requsetmanager.request.NetworkRequestImpl



/**
 * Created by jianjianhong on 18-12-22
 */
class CheesesDataSource(private val context: Context, private val retryExecutor: Executor): ItemKeyedDataSource<Int, Cheese>() {

    private val TAG = "CheesesDataSource"
    private var startPosition:Int = 0

    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(c: LoadInitialParams<Int>, callback: LoadInitialCallback<Cheese>) {
        Log.i(TAG, "loadInitial")
        loadData(callback)

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Cheese>) {
        Log.i(TAG, "loadAfter")
        loadData(callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Cheese>) {
        Log.i(TAG, "loadBefore")
    }

    override fun getKey(item: Cheese): Int {
        return item.id
    }

    private fun loadData(callback: LoadCallback<Cheese>) {
        networkState.postValue(NetworkState.LOADING)
        NetworkRequestImpl.create(context)
            .setUrl("https://api.github.com/users/Anima18/followers")
            .setMethod("GET")
            .setDataType(object : TypeToken<List<Cheese>>() {}.type)
            .send<List<Cheese>>(object : DataRequestCallback<List<Cheese>> {
                override fun onResult(data: List<Cheese>?, status: ResponseStatus?) {
                    if(status!!.code == 200) {
                        retry = null
                        if(startPosition < 500) {
                            callback.onResult(data!!)
                            startPosition += data!!.size
                            networkState.postValue(NetworkState.LOADED)
                        }else {
                            networkState.postValue(NetworkState.error("not data!"))
                        }

                    }else {
                        retry = {
                            loadData(callback)
                        }
                        networkState.postValue(NetworkState.error(status.message))
                    }
                }
            })
    }
}
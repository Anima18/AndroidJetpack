package paging.android.example.com.pagingsample.datasource

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.chris.androidjetpack.piging.Cheese

/**
 * Created by jianjianhong on 18-12-22
 */
class CheesesDataSource: ItemKeyedDataSource<Int, Cheese>() {

    private val TAG = "CheesesDataSource"
    private var startPosition:Int = 0

    override fun loadInitial(c: LoadInitialParams<Int>, callback: LoadInitialCallback<Cheese>) {
        Log.i(TAG, "loadInitial")
        val list = loadData(startPosition, c.requestedLoadSize)
        callback.onResult(list)
        startPosition += list.size
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Cheese>) {
        Log.i(TAG, "loadAfter")
        val list = loadData(startPosition, params.requestedLoadSize)
        callback.onResult(list)
        startPosition += list.size
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Cheese>) {
        Log.i(TAG, "loadBefore")
    }

    override fun getKey(item: Cheese): Int {
        return item.id
    }

    private fun loadData(startPosition:Int, limit: Int): List<Cheese> {
        val list = ArrayList<Cheese>()

        if(startPosition < 500) {
            for (i in 0 until limit) {
                var index = startPosition + i
                list.add(Cheese(index, "Cheese $index"))
            }
        }
        return list
    }
}
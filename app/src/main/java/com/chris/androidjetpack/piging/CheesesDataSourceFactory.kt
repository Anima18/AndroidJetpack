package paging.android.example.com.pagingsample.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.chris.androidjetpack.piging.Cheese
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by jianjianhong on 18-12-22
 */
class CheesesDataSourceFactory(var context: Context): DataSource.Factory<Int, Cheese>() {
    val sourceLiveData = MutableLiveData<CheesesDataSource>()
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    override public fun create(): DataSource<Int, Cheese> {
        val source = CheesesDataSource(context, NETWORK_IO)
        sourceLiveData.postValue(source)
        return source
    }

    fun getNetworkExecutor(): Executor = NETWORK_IO
}
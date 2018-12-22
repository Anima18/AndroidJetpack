package paging.android.example.com.pagingsample.datasource

import androidx.paging.DataSource
import com.chris.androidjetpack.piging.Cheese

/**
 * Created by jianjianhong on 18-12-22
 */
class CheesesDataSourceFactory: DataSource.Factory<Int, Cheese>() {
    override public fun create(): DataSource<Int, Cheese> = CheesesDataSource()
}
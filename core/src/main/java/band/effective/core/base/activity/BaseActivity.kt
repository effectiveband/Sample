package band.effective.core.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import band.effective.core.base.RootViewBinder
import band.effective.core.navigation.flow.FlowBinder
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes private val res: Int?) : AppCompatActivity() {

    @Inject
    lateinit var rootViewBinder: RootViewBinder

    @Inject
    lateinit var flowBinder: FlowBinder

    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        res?.let { id ->
            val root = rootViewBinder.getRootViewContainer(this)
            val content = LayoutInflater.from(this).inflate(id, root, false)
            root.addView(content)
        }
    }

    override fun onStart() {
        super.onStart()
        flowBinder.bind(this)
    }

    override fun onStop() {
        flowBinder.unbind(this)
        super.onStop()
    }
}
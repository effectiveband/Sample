package band.effective.about.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import band.effective.about.R
import band.effective.about.databinding.FragmentAboutBinding
import band.effective.core.base.viewBinding
import band.effective.core.di.InjectedKey
import javax.inject.Inject
import javax.inject.Named

class AboutFragment @Inject constructor(
    @Named(InjectedKey.Configuration.VERSION_NAME) private val appVersion: String,
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_about) {

    private val viewModel: AboutViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding { FragmentAboutBinding.bind(it.requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            aboutAppVersionText.text = appVersion
            aboutOpenApiButton.setOnClickListener {
                viewModel.sendEvent(AboutEvent.OpenApi)
            }
        }
    }
}
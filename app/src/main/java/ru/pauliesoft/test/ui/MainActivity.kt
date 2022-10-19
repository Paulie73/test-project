package ru.pauliesoft.test.ui

import android.os.Bundle
import androidx.activity.viewModels
import ru.pauliesoft.test.databinding.ActivityMainBinding
import ru.pauliesoft.test.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLoaderLayout(binding.loaderLayout.root)
    }
}
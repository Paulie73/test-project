package ru.pauliesoft.test.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.pauliesoft.test.R
import ru.pauliesoft.test.databinding.FragmentResultBinding
import ru.pauliesoft.test.ui.MainViewModel
import ru.pauliesoft.test.ui.base.BaseFragment
import ru.pauliesoft.test.ui.result.tabs.GraphFragment
import ru.pauliesoft.test.ui.result.tabs.TableFragment

@AndroidEntryPoint
class ResultFragment : BaseFragment() {

    override lateinit var binding: FragmentResultBinding
    override val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews() {
        setupViewPager()
    }

    override fun setupObservers() {

    }

    override fun initScreen() {

    }

    private fun setupViewPager() {
        val adapter = TabsPagerAdapter(this)
        with(binding) {
            viewPager.isUserInputEnabled = false
            viewPager.adapter = adapter

            adapter.pages.forEach {
                tabLayout.addTab(tabLayout.newTab().setText(it.first))
            }
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                    viewModel.numberTabSelected = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            tabLayout.selectTab(tabLayout.getTabAt(viewModel.numberTabSelected))
        }
    }

    inner class TabsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        val pages = listOf(
            getString(R.string.table) to TableFragment(),
            getString(R.string.graph) to GraphFragment()
        )

        override fun getItemCount(): Int {
            return pages.size
        }

        override fun createFragment(position: Int): Fragment {
            return pages[position].second
        }
    }
}
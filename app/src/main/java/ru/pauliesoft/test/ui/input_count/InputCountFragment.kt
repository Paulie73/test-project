package ru.pauliesoft.test.ui.input_count

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.pauliesoft.test.databinding.FragmentInputCountBinding

class InputCountFragment : Fragment() {

    private lateinit var binding: FragmentInputCountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputCountBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}
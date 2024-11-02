package com.example.globeway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.globeway.databinding.FragmentOnboarding1Binding
import com.bumptech.glide.Glide // Import Glide library for efficient image loading

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboarding1Binding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_IMAGE_RES_ID = "image_res_id"

        fun newInstance(title: String, description: String, imageResId: Int): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            args.putInt(ARG_IMAGE_RES_ID, imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.textTitle.text = it.getString(ARG_TITLE)
            binding.textDescription.text = it.getString(ARG_DESCRIPTION)

            // Load the image efficiently using Glide
            Glide.with(this)
                .load(it.getInt(ARG_IMAGE_RES_ID))
                .into(binding.imageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

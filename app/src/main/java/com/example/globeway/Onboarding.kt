package com.example.globeway

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Onboarding : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var buttonNext: Button
    private lateinit var backgroundImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        buttonNext = findViewById(R.id.btnNext)
        backgroundImage = findViewById(R.id.backgroundImage) // Background ImageView

        // Set up the adapter and add fragments to it
        val onboardingAdapter = OnboardingPagerAdapter(this)
        onboardingAdapter.addFragment(OnboardingFragment.newInstance(
            "LIFE IS SHORT AND THE WORLD IS WIDE",
            "At Globeway, we simplify your travel goals and unify all your needs in one place",
            R.drawable.scenery // This is the fragment's foreground image
        ))
        onboardingAdapter.addFragment(OnboardingFragment.newInstance(
            "GO OUT THERE AND EXPLORE",
            "To get the best of your adventures, our trip planner will take care of your problems",
            R.drawable.scenerysec
        ))
        onboardingAdapter.addFragment(OnboardingFragment.newInstance(
            "PEOPLE DON'T TAKE TRIPS, TRIPS TAKE PEOPLE",
            "Explore never-seen-before locations with AI-powered recommendations & our Hidden Gems feature",
            R.drawable.scenerythre
        ))

        viewPager.adapter = onboardingAdapter

        // Set initial background image
        backgroundImage.setImageResource(R.drawable.scenery)

        // Set offscreen page limit to preload adjacent fragments and reduce lag
        viewPager.offscreenPageLimit = 2

        // Handle page change events to update the background image
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val backgroundImageResId = when (position) {
                    0 -> R.drawable.scenery
                    1 -> R.drawable.scenerysec
                    2 -> R.drawable.scenerythre
                    else -> R.drawable.scenery // Default fallback
                }
                backgroundImage.setImageResource(backgroundImageResId)
            }
        })

        // Link the TabLayout (indicator) with ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // Handle button click for navigation
        buttonNext.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem + 1 < onboardingAdapter.itemCount) {
                viewPager.currentItem = currentItem + 1
            } else {
                // Finish onboarding process or start a new activity
                finishOnboarding()
            }
        }
    }

    private fun finishOnboarding() {
        val intent = Intent(this, Homepage::class.java)
        startActivity(intent)
        finish()
        finish() // Close this activity
    }
}

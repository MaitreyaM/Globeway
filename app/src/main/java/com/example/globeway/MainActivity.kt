package com.example.globeway

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val SPLASH_DURATION: Long = 2000 // 2000 milliseconds (2 seconds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the root view (your ConstraintLayout or any other layout)
        val rootView: View = findViewById(android.R.id.content)

        // Apply fade-in animation
        rootView.alpha = 0f // Initially invisible
        rootView.animate()
            .alpha(1f) // Fully visible
            .setDuration(SPLASH_DURATION / 2) // Half of the total duration
            .start()

        // Simulate a delay
        Handler().postDelayed({
            // Apply fade-out animation
            rootView.animate()
                .alpha(0f) // Fully invisible
                .setDuration(SPLASH_DURATION / 2) // Half of the total duration
                .withEndAction {
                    // Start the next activity (e.g., Onboarding)
                    val intent = Intent(this, Onboarding::class.java)
                    startActivity(intent)
                    finish() // Close the splash screen activity
                }
                .start()
        }, SPLASH_DURATION / 2) // Delay for the remaining half of the total duration
    }
}

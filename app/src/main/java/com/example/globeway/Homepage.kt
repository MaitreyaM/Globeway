package com.example.globeway

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Homepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)

        // Find the WebView in your layout
        val webView: WebView = findViewById(R.id.webview)

        // Enable JavaScript (if needed)
        webView.settings.javaScriptEnabled = true

        // Set a WebViewClient to handle page navigation within the WebView
        webView.webViewClient = WebViewClient()

        // Load a web page (replace with your desired URL)
        webView.loadUrl("https://globeway.vercel.app/")

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

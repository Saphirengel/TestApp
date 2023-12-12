package com.example.testapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class BewegungsFragment: Fragment() {
    private lateinit var imageView: ImageView
    private var initialX = 0f
    private var initialY = 0f

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bewegungs_test, container, false)
        imageView = view.findViewById(R.id.imageView)

        // Füge einen Touchlistener zur ImageView hinzu
        imageView.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Speichere die Anfangsposition des Klicks
                    initialX = view.x - event.rawX
                    initialY = view.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    // Bewege die View basierend auf der Bewegung des Fingers
                    view.animate()
                        .x(event.rawX + initialX)
                        .y(event.rawY + initialY)
                        .setDuration(0)
                        .start()
                }
                else -> return@setOnTouchListener false
            }
            return@setOnTouchListener true
        }

        return view
    }
}
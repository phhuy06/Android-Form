package com.example.gmail
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val emails = listOf(
            Email("Edurila.com", "Learn Web Design with our best-selling course...", "12:34 PM", false, Color.BLUE),
            Email("Chris Abad", "Help make Campaign Monitor better...", "11:22 AM", true, Color.RED),
            Email("Tuto.com", "Photoshop, SEO, Blender, CSS, WordPress...", "11:04 AM", false, Color.GREEN),
            Email("Support", "OVH services - http://www.ovh.com", "10:26 AM", false, Color.GRAY),
            Email("Matt from Ionic", "The new Ionic Creator is here!", "9:58 AM", true, Color.MAGENTA)
        )

        val adapter = EmailAdapter(emails)
        recyclerView.adapter = adapter
    }
}

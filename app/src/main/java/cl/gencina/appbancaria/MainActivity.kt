package cl.gencina.appbancaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gencina.appbancaria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
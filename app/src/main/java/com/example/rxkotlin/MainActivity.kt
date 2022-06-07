package com.example.rxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.rxkotlin.databinding.ActivityMainBinding
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val searchTextObservable=createButtonObservable()
        searchTextObservable.subscribe{
            binding.tv.text=it
        }
    }

    private fun createButtonObservable(): Observable<String> {
        return Observable.create { emitter ->
            binding.edit.addTextChangedListener{
                emitter.onNext(it.toString())
            }

//            binding.button.setOnClickListener {
//                emitter.onNext(binding.edit.text.toString())
//            }
            emitter.setCancellable(null)

        }

    }
}
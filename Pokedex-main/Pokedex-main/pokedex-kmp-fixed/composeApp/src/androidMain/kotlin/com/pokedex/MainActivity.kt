package com.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pokedex.data.remote.PostApi
import com.pokedex.data.remote.createHttpClient
import com.pokedex.data.repository.PostRepository
import com.pokedex.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val postApi = PostApi(createHttpClient())
        val postRepository = PostRepository(postApi)
        val postViewModel = PostViewModel(postRepository)

        setContent {
            App(postViewModel = postViewModel)
        }
    }
}
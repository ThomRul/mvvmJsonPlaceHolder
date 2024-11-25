package com.example.mvvmcallapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcallapi.view.adapter.UserAdapter
import com.example.mvvmcallapi.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser les vues
        recyclerView = findViewById(R.id.recyclerView)

        // Observer les changements dans le ViewModel
        viewModel.user.observe(this) { users ->
            adapter = UserAdapter(users)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }

        // Configurer le bouton de création
        findViewById<Button>(R.id.createButton).setOnClickListener {
            val name = findViewById<EditText>(R.id.nameEditText).text.toString()
            val email = findViewById<EditText>(R.id.emailEditText).text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                viewModel.createUser(name, email)
                // Vider les champs
                findViewById<EditText>(R.id.nameEditText).text.clear()
                findViewById<EditText>(R.id.emailEditText).text.clear()
            }
        }

        // Charger les données initiales
        viewModel.loadUsers()
    }
}
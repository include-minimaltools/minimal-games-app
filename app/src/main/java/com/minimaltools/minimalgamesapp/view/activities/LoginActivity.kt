package com.minimaltools.minimalgamesapp.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.minimaltools.minimalgamesapp.R
import com.minimaltools.minimalgamesapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        with(binding) {
            btnSignup.setOnClickListener {
                try{
                    val intentSignupActivity = Intent(this@LoginActivity, SignupActivity::class.java)
                    startActivity(intentSignupActivity)
                } catch (e: Exception) {
                    println(e.message)
                }
            }

            btnLogin.setOnClickListener {
                try {
                    val email = inputEmail.text.toString()
                    val password = inputPassword.text.toString()

                    if(validateInputs(email, password)) {
                        login(email, password)
                    }

                } catch (e: Exception) {
                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }

            setTheme(R.style.Theme_MinimalGamesApp)
            setContentView(root)
        }
    }

    private fun login(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else
                    Toast.makeText(
                        this@LoginActivity, getString(R.string.msg_user_password_incorrect),
                        Toast.LENGTH_LONG
                    ).show()

                binding.inputPassword.setText("") // clear password input
            }
    }

    private fun validateInputs(email: String, password: String) : Boolean {
        var valid = true
        if (email.isEmpty()) {
            binding.inputEmail.error = getString(R.string.msg_email_required)
            valid = false
        }

        if (!email.contains("\\w+@\\w+\\.\\w+".toRegex())) {
            binding.inputEmail.error = getString(R.string.msg_email_invalid)
            valid = false
        }

        if (password.isEmpty()) {
            binding.inputPassword.error = getString(R.string.msg_password_required)
            valid = false
        }

        return valid
    }
}
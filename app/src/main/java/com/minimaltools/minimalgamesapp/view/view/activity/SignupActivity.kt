package com.minimaltools.minimalgamesapp.view.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.minimaltools.minimalgamesapp.R
import com.minimaltools.minimalgamesapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        with (binding) {
            btnCreateAccount.setOnClickListener {
                if(!validateForm())
                    return@setOnClickListener

                createAccount(inputEmail.text.toString(), inputPassword.text.toString()) { success ->
                    if(success) {
                        Toast.makeText(this@SignupActivity, getString(R.string.msg_account_created), Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else
                        Toast.makeText(this@SignupActivity, R.string.msg_account_created_error, Toast.LENGTH_SHORT).show()
                }
            }

            btnBack.setOnClickListener {
                finish()
            }

            setContentView(root)
        }
    }

    private fun validateForm() : Boolean {
        var result = false
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        val confirmPassword = binding.inputConfirmPassword.text.toString()

        if (email.isEmpty())
            binding.inputEmail.error = "Email is required"

        else if (!email.contains("\\w+@\\w+\\.\\w+".toRegex()))
            binding.inputEmail.error = getString(R.string.msg_email_invalid)

        else if (password.isEmpty())
            binding.inputPassword.error = getString(R.string.msg_password_required)

        else if (password.length < 6)
            binding.inputPassword.error = getString(R.string.msg_password_length_invalid)

        else if (confirmPassword.isEmpty())
            binding.inputConfirmPassword.error = getString(R.string.msg_confirm_password_required)

        else if (confirmPassword != password)
            binding.inputConfirmPassword.error = getString(R.string.msg_password_confirm_invalid)

        else
            result = true

        return result
    }

    private fun createAccount(email: String, password: String, onFinish: (result: Boolean) -> Unit) {
        var result = true
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            task.exception?.let {
                result = false
                Toast.makeText(this@SignupActivity, it.message, Toast.LENGTH_SHORT).show()
            }
            onFinish(result)
        }
    }
}
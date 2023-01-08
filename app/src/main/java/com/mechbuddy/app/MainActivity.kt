package com.mechbuddy.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mechbuddy.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth //create a firebase auth instance
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance() //instantiate the auth object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Register.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.Login.setOnClickListener{
            if(checking()){
                val email = binding.Email.text.toString()
                val password = binding.Pass.text.toString()
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                            task->
                        if(task.isSuccessful){
                            //Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Wrong details", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun checking():Boolean{
        if(binding.Email.text.toString().trim{it<=' '}.isNotEmpty()&&binding.Pass.text.toString().trim{it<=' '}.isNotEmpty()){
            return true
        }
        return false
    }
}
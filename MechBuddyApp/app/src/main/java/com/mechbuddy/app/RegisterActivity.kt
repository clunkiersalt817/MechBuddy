package com.mechbuddy.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mechbuddy.app.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var db:FirebaseFirestore
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Signup.setOnClickListener {
            if(checking()){
                val email=binding.EmailRegister.text.toString()
                val password=binding.PasswordRegister.text.toString()
                val name=binding.Name.text.toString()
                val phone=binding.Phone.text.toString()
                val user = hashMapOf(
                    "Name" to name,
                    "Phone" to phone,
                    "Email" to email
                )
                val Users = db.collection("Users")
                Users.whereEqualTo("Email",email).get()
                    .addOnSuccessListener{
                            that->
                        if(that.isEmpty){
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this)
                                {tasks->
                                    if(tasks.isSuccessful){
                                        Users.document(email).set(user)
                                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this,HomeActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else{
                                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                        else{
                            Toast.makeText(this, "User Already Registered", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                    }

            }
            else{
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checking():Boolean{
        if(binding.Name.text.toString().trim{it<=' '}.isNotEmpty()
            && binding.Phone.text.toString().trim { it<=' ' }.isNotEmpty()
            && binding.EmailRegister.text.toString().trim { it<=' ' }.isNotEmpty()
            && binding.PasswordRegister.text.toString().trim { it<=' ' }.isNotEmpty()
        ){
            return true
        }
        return false
    }
}
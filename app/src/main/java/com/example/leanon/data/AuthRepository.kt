package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.models.User
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.navigation.ROUTE_SIGNUP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository(var navController: NavHostController, var context: Context) {
    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading...")
        progress.setMessage("Please wait...")
    }

    fun signup(username: String, email: String, password: String) {
        progress.show()
        progress.dismiss()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                var userData = User(username, email, password, mAuth.currentUser!!.uid)
                var regRef = FirebaseDatabase.getInstance().getReference()
                    .child("Users" + mAuth.currentUser!!.uid)
                regRef.setValue(userData).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(BottomBarScreen.Home.route)
                    } else {
                        Toast.makeText(context, "Error: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                        navController.navigate(ROUTE_LOGIN)
                    }
                }
            }
            else{
                navController.navigate(ROUTE_SIGNUP)
            }
        }
    }
    fun login(email: String,password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Home.route)
            }
            else{
                Toast.makeText(context, "Error: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                navController.navigate(ROUTE_LOGIN)
            }
        }
    }
    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }
    fun isLoggedIn():Boolean = mAuth.currentUser != null
}
package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.models.User
import com.example.leanon.models.UserDets
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.navigation.ROUTE_SIGNUP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class  AuthRepository(var navController: NavHostController, var context: Context) {
    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading...")
        progress.setMessage("Please wait...")
    }
    /*----Sign Up Logic---*/
    fun signup(username: String,email: String,password: String) {
        try {
            progress.show()
            progress.dismiss()
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful) {
                    var userData = User(email,password,mAuth.currentUser!!.uid)
                    var regRef = FirebaseDatabase.getInstance().getReference()
                        .child("Users" + mAuth.currentUser!!.uid)
                    regRef.setValue(userData).addOnCompleteListener {

                        var userDets = UserDets(username)
                        FirebaseDatabase.getInstance().getReference().child("User Details")
                            .child(mAuth.currentUser!!.uid).setValue(userDets)
                        if (it.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Thank you $username for joining us!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(BottomBarScreen.Home.route)
//                        } else {
////                            Toast.makeText(
////                                context,
////                                "Error: Not successful or the user already exists",
////                                Toast.LENGTH_SHORT
////                            ).show()
//                            navController.navigate(ROUTE_LOGIN)
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Error: Not successful or the user already exists",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(ROUTE_SIGNUP)
                }
            }
        }
        catch (e:Exception){
            Toast.makeText(
                context,
                "Kindly add all your details to signup.",
                Toast.LENGTH_LONG).show()
        }
    }

    /*----Log in logic---*/
    fun login(email: String,password: String){
        try {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(BottomBarScreen.Home.route)
                } else {
                    Toast.makeText(
                        context,
                        "The account doesn't exist or the details entered are wrong.",
                        Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_LOGIN)
                }
            }
        }
        catch (e:Exception){
            Toast.makeText(
                context,
                "Kindly add your details to login.",
                Toast.LENGTH_LONG).show()
        }
    }
    /*----Log Out Logic---*/
    fun logout(){
        mAuth.signOut()
        Toast.makeText(context, "You've been logged out", Toast.LENGTH_SHORT).show()
        navController.navigate(ROUTE_LOGIN)
    }

    /*----Log in Restriction Logic---*/
    fun isLoggedIn():Boolean = mAuth.currentUser != null
    fun viewAuth(auth: MutableState<User>, auths: SnapshotStateList<User>): SnapshotStateList<User> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Users")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                auths.clear()
                val value = snapshot.getValue(User::class.java)
                auth.value = value!!
                auths.add(value)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return auths
    }
    fun viewProfile(){
        var username: String
        var email: String
        val user = mAuth.currentUser
        if (user != null){
            user.let {
                username = it.displayName.toString()
                email = it.email.toString()
            }
        }
        else{
            navController.navigate(ROUTE_LOGIN)
        }
    }
}

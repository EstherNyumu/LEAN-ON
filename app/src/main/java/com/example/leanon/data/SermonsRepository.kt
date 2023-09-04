package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.models.Sermons
import com.example.leanon.navigation.ROUTE_ADD_POST
import com.example.leanon.navigation.ROUTE_LOGIN
import com.google.firebase.database.FirebaseDatabase

class SermonsRepository(var navController: NavHostController,var context: Context) {
    var authRepository:AuthRepository
    var progress: ProgressDialog
    var sermons:ArrayList<Sermons>
    init {
        authRepository = AuthRepository(navController,context)
        if(!authRepository.isLoggedIn()){
            navController.navigate(ROUTE_LOGIN)
        }
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        sermons = mutableListOf<Sermons>()as ArrayList<Sermons>
    }
    fun saveSermon(sermonDate:String,preacher:String,sermonScripture:String,sermonTopic:String,sermonNotes:String){
        var id = System.currentTimeMillis().toString()
        var sermonData = Sermons(sermonDate,preacher,sermonScripture,sermonTopic,sermonNotes,id)
        var sermonRef = FirebaseDatabase.getInstance().getReference().child("Sermons/$id")
        progress.show()

        sermonRef.setValue(sermonData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"You can go through today's sermon later", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Home.route)
            }
            else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                navController.navigate(ROUTE_ADD_POST)
            }
        }
    }
    fun viewPosts(){

    }
    fun deletePost(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference().child("Sermons/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if(it.isSuccessful){
                Toast.makeText(context, "Sermon deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun editPost(){

    }
}
package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.models.Sermons
import com.example.leanon.navigation.ROUTE_ADD_POST
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SermonsRepository(var navController: NavHostController,var context: Context) {
//    var authRepository:AuthRepository
    var progress: ProgressDialog
//    var sermons:ArrayList<Sermons>
    init {
//        authRepository = AuthRepository(navController,context)
//        if(!authRepository.isLoggedIn()){
//            navController.navigate(ROUTE_LOGIN)
//        }
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
//        sermons = mutableListOf<Sermons>()as ArrayList<Sermons>
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
    fun viewSermon(sermon: MutableState<Sermons>, mySermons: SnapshotStateList<Sermons>): SnapshotStateList<Sermons> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Sermons")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                mySermons.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Sermons::class.java)
                    sermon.value = value!!
                    mySermons.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return mySermons
    }


    fun deleteSermon(id:String){
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
    fun editSermon(){

    }
}
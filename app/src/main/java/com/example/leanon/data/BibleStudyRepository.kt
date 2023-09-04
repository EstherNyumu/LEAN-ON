package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.leanon.models.BibleStudy
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.navigation.ROUTE_ADD_BIBLE_STUDY
import com.example.leanon.navigation.ROUTE_LOGIN
import com.google.firebase.database.FirebaseDatabase

class BibleStudyRepository(var navController: NavHostController,var context: Context){
    var authRepository:AuthRepository
    var progress: ProgressDialog
    var bibleStudy:ArrayList<BibleStudy>
    init {
        authRepository = AuthRepository(navController,context)
        if(!authRepository.isLoggedIn()){
            navController.navigate(ROUTE_LOGIN)
        }
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        bibleStudy = mutableListOf<BibleStudy>()as ArrayList<BibleStudy>
    }
    fun saveStudy(studyDate:String,studyScripture:String,observation:String,application:String,studyPrayer:String){
        var id = System.currentTimeMillis().toString()
        var studyData = BibleStudy(studyDate,studyScripture,observation,application,studyPrayer,id)
        var studyRef = FirebaseDatabase.getInstance().getReference().child("Bible Study/$id")
        progress.show()

        studyRef.setValue(studyData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Study Done!", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Home.route)
            }
            else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                navController.navigate(ROUTE_ADD_BIBLE_STUDY)
            }
        }
    }
    fun viewStudy(){

    }
    fun deleteStudy(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference().child("Bible Study/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if(it.isSuccessful){
                Toast.makeText(context, "Study deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun editPost(){

    }
}
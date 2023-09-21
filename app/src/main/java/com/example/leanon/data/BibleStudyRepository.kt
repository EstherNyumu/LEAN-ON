package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.leanon.models.BibleStudy
import com.example.leanon.navigation.ROUTE_ADD_BIBLE_STUDY
import com.example.leanon.navigation.ROUTE_BIBLE_STUDY_NOTEPAD
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BibleStudyRepository(var navController: NavHostController,var context: Context){
//    var authRepository:AuthRepository
    var progress: ProgressDialog
//    var bibleStudy:ArrayList<BibleStudy>
    init {
//        authRepository = AuthRepository(navController,context)
//        if(!authRepository.isLoggedIn()){
//            navController.navigate(ROUTE_LOGIN)
//        }
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
//        bibleStudy = mutableListOf<BibleStudy>()as ArrayList<BibleStudy>
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
                navController.navigate(ROUTE_BIBLE_STUDY_NOTEPAD)
            }
            else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                navController.navigate(ROUTE_ADD_BIBLE_STUDY)
            }
        }
    }
    fun viewStudy(study: MutableState<BibleStudy>, studies: SnapshotStateList<BibleStudy>): SnapshotStateList<BibleStudy> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Bible Study")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                studies.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(BibleStudy::class.java)
                    study.value = value!!
                    studies.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return studies
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
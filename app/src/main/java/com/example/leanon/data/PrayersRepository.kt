package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.leanon.models.Prayers
import com.example.leanon.navigation.ROUTE_ADD_PRAYER
import com.example.leanon.navigation.ROUTE_PRAYER_NOTEPAD
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PrayersRepository(var navController: NavHostController, var context: Context) {
    var progress: ProgressDialog
    init {
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }
    /*----Saving Data Logic---*/
    fun savePrayer(prayerDate: String,prayerTime:String,prayerText:String){
        var id = System.currentTimeMillis().toString()
        var prayerData = Prayers(prayerDate,prayerTime,prayerText,id)
        var prayerRef = FirebaseDatabase.getInstance().getReference().child("Prayers/$id")
        progress.show()

        prayerRef.setValue(prayerData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Amen to your prayer!", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_PRAYER_NOTEPAD)
            }
            else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                navController.navigate(ROUTE_ADD_PRAYER)
            }
        }
    }
    /*----Viewing Data Logic---*/
    fun viewPrayers(prayer: MutableState<Prayers>, myPrayers: SnapshotStateList<Prayers>): SnapshotStateList<Prayers> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Prayers")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                myPrayers.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Prayers::class.java)
                    prayer.value = value!!
                    myPrayers.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return myPrayers
    }
    /*----Deleting Data Logic---*/
    fun deletePrayer(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference().child("Prayers/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if(it.isSuccessful){
                Toast.makeText(context, "Prayer deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
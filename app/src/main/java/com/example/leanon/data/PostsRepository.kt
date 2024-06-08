package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.models.PostsWithImage
import com.example.leanon.models.PostsWithoutImage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class PostsRepository(var navController: NavHostController, var context: Context) {
    var progress:ProgressDialog
    init {
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    /*----Deleting Data Logic---*/
    fun deletePost(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference().child("Posts/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if(it.isSuccessful){
                Toast.makeText(context, "Post deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    /*----Saving Data Logic---*/
    fun savePostWithImage(anonymousName: String, postText:String,filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Posts/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = PostsWithImage(anonymousName,postText,imageUrl,id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Posts/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(BottomBarScreen.Home.route)
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun savePostWithoutImage(anonymousName: String, postText:String){
        var id = System.currentTimeMillis().toString()
        var postData = PostsWithoutImage(anonymousName,postText,id)
        var postRef = FirebaseDatabase.getInstance().getReference().child("Posts/$id")
        progress.show()

        postRef.setValue(postData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Post added!", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Home.route)
            }
            else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                navController.navigate(BottomBarScreen.Home.route)
            }
        }

    }


    /*----Viewing Data Logic---*/
    fun viewPosts(post:MutableState<PostsWithImage>, myPosts:SnapshotStateList<PostsWithImage>): List<PostsWithImage> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Posts")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                myPosts.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(PostsWithImage::class.java)
                    post.value = value!!
                    myPosts.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
//        return myPosts.sortedByDescending { it.postId }
        return myPosts
    }
}
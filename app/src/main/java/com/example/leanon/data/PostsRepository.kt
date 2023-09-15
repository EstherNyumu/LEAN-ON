package com.example.leanon.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.models.Posts
import com.example.leanon.navigation.ROUTE_ADD_POST
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PostsRepository(var navController: NavHostController, var context: Context) {
//    var authRepository:AuthRepository
    var progress:ProgressDialog
//    var posts:ArrayList<Posts>
    init {
//        authRepository = AuthRepository(navController,context)
//        if(!authRepository.isLoggedIn()){
//            navController.navigate(ROUTE_LOGIN)
//        }
        progress= ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
//        posts = mutableListOf<Posts>()as ArrayList<Posts>
    }
    fun savePosts(anonymousName:String,postText: String){
        var id = System.currentTimeMillis().toString()
        var postData = Posts(anonymousName,postText,id)
        var postRef = FirebaseDatabase.getInstance().getReference().child("Posts/$id")
        progress.show()

        postRef.setValue(postData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Your post has been added", Toast.LENGTH_SHORT).show()
                navController.navigate(BottomBarScreen.Home.route)
            }
            else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                navController.navigate(ROUTE_ADD_POST)
            }
        }
    }
    fun viewPosts(post: MutableState<Posts>, myPosts: SnapshotStateList<Posts>): SnapshotStateList<Posts> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Posts")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                myPosts.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Posts::class.java)
                    post.value = value!!
                    myPosts.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return myPosts
    }

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
    fun editPost(){

    }
}
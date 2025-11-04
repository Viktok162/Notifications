//package ru.netology.nmedia.repository
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import ru.netology.nmedia.dto.Post
//
//class PostRepositorySharedPrefImpl(context: Context): PostRepository {
//    private val pref = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
//    private var index: Long = 1L
//    private var posts = emptyList<Post>()
//        set(value){
//            field = value
//            data.value = posts
//            sync()
//        }
//    private val data = MutableLiveData(posts)
//
//    init{
//        pref.getString(POSTS_KEY, null)?.let { json ->
//            posts = gson.fromJson(json, type)
//            index = (posts.maxOfOrNull { post -> post.id }?: 0) + 1
//        }
//    }
//
//    override fun get(): LiveData<List<Post>> = data
//
//    override fun like(id: Long) {
//        posts = posts.map { post ->
//            if (post.id == id) {
//                val i = if (post.likeByMe) -1 else 1
//                post.copy(likes = post.likes + i, likeByMe = !post.likeByMe)
//            } else {
//                post
//            }
//        }
////        data.value = posts
////        sync()
//    }
//
//    override fun share(id: Long) {
//        posts = posts.map { post ->
//            if (post.id == id) {
//                post.copy(shares = post.shares + 1)
//            } else post
//        }
////        data.value = posts
////        sync()
//    }
//
//    override fun look(id: Long) {
//        posts = posts.map { post ->
//            if (post.id == id) {
//                post.copy(looks = post.looks + 1)
//            } else post
//        }
////        data.value = posts
////        sync()
//    }
//
//    override fun removeById(id: Long) {
//        posts = posts.filter { it.id != id }
////        data.value = posts
////        sync()
//    }
//
//    override fun save(post: Post) {
//        posts = if(post.id == 0L) {
//             listOf(
//                post.copy(
//                    id = index++,
//                    author = "Me",
//                    published = "now"
//                )
//            ) + posts
//        } else {
//            posts.map{
//                if (post.id == it.id){
//                    it.copy(content = post.content)
//                } else it
//            }
//        }
////        data.value = posts
////        sync()
//        return
//    }
//
//    private fun sync(){
//        pref.edit().apply() {
//            putString(POSTS_KEY, gson.toJson(posts, type))
//            apply()
//        }
//    }
//
//    companion object {
//        private const val SHARED_PREF_FILE = "repo"
//        private const val POSTS_KEY = "posts"
//        private val gson = Gson()
//        private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
//
//    }
//}
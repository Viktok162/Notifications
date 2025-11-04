package ru.netology.nmedia.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post


@Entity(tableName = "Post_Entity")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("author")
    val author: String ="",
    @ColumnInfo("published")
    val published: String ="",
    val content: String ="",
    val likes: Int = 999,
    val shares: Int = 998,
    val looks: Int = 997,
    val likeByMe: Boolean = false,
    val video: String? = null
) {

    fun toDto() = Post(
        id = id,
        author = author,
        published = published,
        content = content,
        likes = likes,
        shares = shares,
        looks = looks,
        likeByMe = likeByMe,
        video = video
    )
// var 1   PostRepositoryImpl
    companion object {
        fun fromDto(post: Post) = post.run {
            PostEntity(
                id = id,
                author = author,
                published = published,
                content = content,
                likes = likes,
                shares = shares,
                looks = looks,
                likeByMe = likeByMe,
                video = video
            )
        }
    }
}
// var 2    PostRepositoryImpl Extension fun
fun Post.toEntity() = PostEntity(
    id = id,
    author = author,
    published = published,
    content = content,
    likes = likes,
    shares = shares,
    looks = looks,
    likeByMe = likeByMe,
    video = video
)
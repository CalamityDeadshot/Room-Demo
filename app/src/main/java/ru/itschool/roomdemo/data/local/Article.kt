package ru.itschool.roomdemo.data.local

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Author::class,
            parentColumns = ["id"],
            childColumns = ["authorId"]
        )
    ]
)
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val text: String,
    val authorId: Long
)

data class ArticleWithAuthor(
    @Embedded val article: Article,
    @Relation(
        parentColumn = "authorId",
        entityColumn = "id"
    )
    val author: Author
)
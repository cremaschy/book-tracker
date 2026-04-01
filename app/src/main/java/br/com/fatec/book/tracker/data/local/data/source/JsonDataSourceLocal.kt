package br.com.fatec.book.tracker.data.local.data.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.fatec.book.tracker.data.data.data.source.JsonDataSource
import br.com.fatec.book.tracker.domain.model.Post
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class JsonDataSourceLocal(
    private val dataSource: DataStore<Preferences>,
) : JsonDataSource.Local {
    private val postsKey = stringPreferencesKey("posts_key")

    override suspend fun getCachedPosts(): List<Post> {
        val json = dataSource.data.map { preferences ->
            preferences[postsKey].orEmpty()
        }.first()
        return if (json.isNotEmpty()) {
            Json.decodeFromString(json)
        } else {
            emptyList()
        }
    }

    override suspend fun savePosts(posts: List<Post>) {
        val json = Json.encodeToString(posts)
        dataSource.edit { preferences ->
            preferences[postsKey] = json
        }
    }
}

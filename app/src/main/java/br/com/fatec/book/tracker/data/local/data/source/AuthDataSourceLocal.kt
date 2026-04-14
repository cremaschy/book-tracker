package br.com.fatec.book.tracker.data.local.data.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.fatec.book.tracker.data.data.data.source.AuthDataSource
import br.com.fatec.book.tracker.domain.model.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class AuthDataSourceLocal(
    private val dataSource: DataStore<Preferences>,
) : AuthDataSource.Local {
    private val userKey = stringPreferencesKey("user_key")

    override val token: Flow<Token?>
        get() = dataSource.data.map { preferences ->
            preferences[userKey]?.let {
                Json.decodeFromString(deserializer = Token.serializer(), string = it)
            }
        }

    override suspend fun auth(): Token? {
        return runCatching {
            dataSource.data.map { preferences ->
                preferences[userKey]?.let {
                    Json.decodeFromString(deserializer = Token.serializer(), string = it)
                }
            }.first()
        }.getOrNull()
    }

    override suspend fun saveToken(token: Token?) {
        dataSource.edit { preferences ->
            if(token == null) {
                preferences.remove(userKey)
                return@edit
            } else {
                preferences[userKey] = Json.encodeToString(Token.serializer(), token)
            }
        }
    }
}

package br.com.fatec.book.tracker.data.local.data.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.fatec.book.tracker.data.data.data.source.TokenDataSource
import br.com.fatec.book.tracker.domain.model.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class TokenDataSourceLocal(
    private val dataSource: DataStore<Preferences>,
) : TokenDataSource.Local {
    private val userKey = stringPreferencesKey("user_key")

    override val token: Flow<Token?>
        get() = dataSource.data.map { preferences ->
            preferences[userKey]?.let {
                Json.decodeFromString(deserializer = Token.serializer(), string = it)
            }
        }

    override suspend fun getToken(): Token? {
        return runCatching {
            dataSource.data.map { preferences ->
                preferences[userKey]?.let {
                    Json.decodeFromString(deserializer = Token.serializer(), string = it)
                }
            }.first()
        }.getOrNull()
    }

    override suspend fun saveToken(token: Token?) {
        dataSource.edit { prefs ->
            if (token == null) {
                prefs.remove(userKey)
                return@edit
            }

            prefs[userKey] = Json.encodeToString(Token.serializer(), token)
        }
    }
}

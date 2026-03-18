package com.example.kbbi.data

import android.app.Application
import android.content.Context
import com.example.kbbi.data.local.KBBIDatabase
import com.example.kbbi.data.repository.KBBIWordRepository
import com.example.kbbi.data.repository.OfflineKBBIWordRepository

interface AppContainer {
    val kbbiWordRepository: KBBIWordRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val kbbiWordRepository: KBBIWordRepository by lazy {
        OfflineKBBIWordRepository(
            KBBIDatabase.getDatabase(context).wordDao(),
            assets = context.assets,
            json = kotlinx.serialization.json.Json
        )
    }
}

class KBBIDataApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer(this)
    }
}
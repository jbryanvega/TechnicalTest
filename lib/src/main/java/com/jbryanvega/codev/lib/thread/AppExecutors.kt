package com.jbryanvega.codev.lib.thread

import android.os.Handler
import android.os.Looper

import java.util.concurrent.Executor
import java.util.concurrent.Executors

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppExecutors
internal constructor(private val diskIO: Executor,
                     private val networkIO: Executor,
                     private val mainThread: Executor) {

    companion object {
        private const val THREAD_COUNT = 5
    }

    @Inject constructor() : this(DiskIOThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT), MainThreadExecutor())

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}

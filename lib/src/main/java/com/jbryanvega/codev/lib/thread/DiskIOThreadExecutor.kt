package com.jbryanvega.codev.lib.thread

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Executor that runs a task on a new background thread.
 */
@Singleton
class DiskIOThreadExecutor @Inject constructor() : Executor {
    private val diskIoExecutor: Executor

    init {
        diskIoExecutor = Executors.newSingleThreadExecutor()
    }

    override fun execute(command: Runnable) {
        diskIoExecutor.execute(command)
    }
}
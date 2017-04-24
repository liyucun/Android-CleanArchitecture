package com.yucun.data.executor;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous execution, but every
 * implementation will execute the job out of the UI thread.
 */
@FunctionalInterface
public interface ThreadExecutor extends Executor {}

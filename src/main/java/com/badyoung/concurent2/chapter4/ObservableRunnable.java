package com.badyoung.concurent2.chapter4;

public abstract class ObservableRunnable implements Runnable {

    protected final LifeCycleListener listener;

    public ObservableRunnable(LifeCycleListener listener) {
        this.listener = listener;
    }

    protected void notifyChange(final RunnableEvent event){
        listener.onEvent(event);
    }

    public enum RunableState{
        RUNNING,ERROR,DONE;
    }

    public static class RunnableEvent{
        private final RunableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }


        public RunableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}

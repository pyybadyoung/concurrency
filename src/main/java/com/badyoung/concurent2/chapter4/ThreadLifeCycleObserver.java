package com.badyoung.concurent2.chapter4;

import java.util.List;

public class ThreadLifeCycleObserver implements LifeCycleListener {

    private static final Object LOCK = new Object();

    public void concueerntQuery(List<String> ids){
        if (ids==null||ids.isEmpty()){
            return;
        }
        ids.stream().forEach(id->new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunableState.RUNNING,Thread.currentThread(),null));
                    System.out.println("query for the id " + id);
                    Thread.sleep(1000);
                    notifyChange(new RunnableEvent(RunableState.DONE,Thread.currentThread(),null));
                }catch (Exception e){
                    notifyChange(new RunnableEvent(RunableState.ERROR,Thread.currentThread(),e));
                }
            }
        },id).start());
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("the runnable ["+event.getThread().getName()+"] data changed and state is ["+event.getState()+"]");

            if (event.getCause()!=null){
                System.out.println("the runnable ["+event.getThread().getName()+"] process failed");
                event.getCause().printStackTrace();
            }
        }
    }
}

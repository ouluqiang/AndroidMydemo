package com.example.my.event;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/5/6.
 */
public class EventBusUtils {

    public static EventBusUtils eventBusUtils;
    private EventBus bus;

    public EventBusUtils() {
        bus=EventBus.getDefault();
    }

    public static EventBusUtils getDefault(){
        if(eventBusUtils==null){
            synchronized (EventBusUtils.class){
                if(eventBusUtils==null){
                    eventBusUtils=new EventBusUtils();
                }
            }
        }
        return eventBusUtils;
    }

    /**
     *发送
     * @param object
     */
    public void post(Object object){
        bus.post(object);
    }

    /**
     *注册
     * @param object
     */
    public void register(Object object){
        bus.register(object);
    }

    /**
     * 取消注册
     * @param object
     */
    public void unregister(Object object){
        bus.unregister(object);
    }

    /**
     * 主线程运行
     * @param <T>
     */
    public static interface onMain<T> {
        public void onEventMainThread(T event);
    }

    /**
     * 事件发送线程中执行
     * @param <T>
     */
    public static interface onPostThread<T> {
        public void onEvent(T event);
    }

    /**
     * 运行在单独的线程中，即非UI线程，也非发送事件的线程
     *
     * @author
     *
     */
    public static interface onAsync<T> {
        public void onEventAsync(T event);
    }


    /**
     * 如果发送事件的线程不是UI线程，则运行在该线程中。如果发送事件的是UI线程，则它运行在由EventBus维护的一个单独的线程中
     *
     * @author
     *
     */
    public static interface onBackgroundThread<T> {
        public void onEventBackgroundThread(T event);
    }



}

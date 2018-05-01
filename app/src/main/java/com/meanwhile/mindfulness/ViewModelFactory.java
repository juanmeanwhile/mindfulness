package com.meanwhile.mindfulness;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.support.annotation.VisibleForTesting;
import android.view.View;

import com.meanwhile.mindfulness.repo.SessionRepository;

import java.util.concurrent.Executors;


/**
 * Created by mengujua on 07/12/17.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;
    private SessionRepository sessionRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    //MyDatabase db = Room.databaseBuilder(application,
                    //        MyDatabase.class, DB_NAME).build();
                    INSTANCE = new ViewModelFactory(application, new SessionRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, SessionRepository repository) {
        mApplication = application;
        sessionRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(sessionRepository);

        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

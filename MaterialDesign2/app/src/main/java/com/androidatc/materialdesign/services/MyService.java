package com.androidatc.materialdesign.services;

import com.androidatc.materialdesign.loggin.L;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

/**
 * Created by jorgecasariego on 8/9/15.
 */
public class MyService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        L.t(this, "onStartJob");
        jobFinished(jobParameters, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        L.t(this, "onStopJob");
        return false;
    }
}

package com.meiya.quartz.example13;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleRecoveryStatefulJob extends SimpleRecoveryJob {

    public SimpleRecoveryStatefulJob() {
        super();
    }
}

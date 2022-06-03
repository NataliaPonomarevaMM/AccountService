package org.example.monitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component
public class JmxBean {
    @Autowired
    private MetricsStorage metricsStorage;

    @ManagedOperation
    public void clearStats() {
        metricsStorage.getMetrics().remove(MetricsStorage.getRequestsName);
        metricsStorage.setGetRequests(metricsStorage.getMetrics().meter(MetricsStorage.getRequestsName));

        metricsStorage.getMetrics().remove(MetricsStorage.postRequestsName);
        metricsStorage.setGetRequests(metricsStorage.getMetrics().meter(MetricsStorage.postRequestsName));
    }
}

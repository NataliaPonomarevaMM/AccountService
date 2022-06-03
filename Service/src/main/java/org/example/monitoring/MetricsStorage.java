package org.example.monitoring;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@Getter
public class MetricsStorage {
    public static final String getRequestsName = "getRequests";
    public static final String postRequestsName = "postRequests";

    private final MetricRegistry metrics = new MetricRegistry();
    @Setter
    private Meter getRequests = metrics.meter(getRequestsName);
    @Setter
    private Meter postRequests = metrics.meter(postRequestsName);

    @PostConstruct
    public void initialize() {
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .outputTo(System.out)
                .build();
        consoleReporter.start(1, TimeUnit.MINUTES);
        JmxReporter jmxReporter = JmxReporter.forRegistry(metrics).build();
        jmxReporter.start();
    }
}

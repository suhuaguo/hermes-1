package pl.allegro.tech.hermes.common.message.wrapper;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import javax.inject.Inject;

import static com.codahale.metrics.MetricRegistry.name;

public class DeserializationMetrics {
    private final MetricRegistry metricRegistry;

    @Inject
    public DeserializationMetrics(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    public Counter errorsForSchemaVersionAwarePayload() {
        return metricRegistry.counter(name(deserializationErrorsPath(), "payloadWithSchemaVersion"));
    }

    public Counter errorsForAnySchemaVersion() {
        return metricRegistry.counter(name(deserializationErrorsPath(), "anySchemaVersion"));
    }

    public Counter errorsForAnyOnlineSchemaVersion() {
        return metricRegistry.counter(name(deserializationErrorsPath(), "anyOnlineSchemaVersion"));
    }

    public Counter missedSchemaVersionInPayload() {
        return metricRegistry.counter(name(deserializationPath(), "missed", "schemaVersionInPayload"));
    }

    private String deserializationErrorsPath() {
        return deserializationPath() + ".errors";
    }

    private String deserializationPath() {
        return "content.avro.deserialization";
    }
}

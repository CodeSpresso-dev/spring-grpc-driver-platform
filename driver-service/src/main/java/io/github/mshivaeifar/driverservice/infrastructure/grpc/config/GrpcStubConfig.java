package io.github.mshivaeifar.driverservice.infrastructure.grpc.config;

import io.github.mshivaeifar.telemetryservice.grpc.TelemetryServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcStubConfig {

    @Bean
    public TelemetryServiceGrpc.TelemetryServiceBlockingStub telemetryStub(
            ManagedChannel telemetryChannel
    ) {

        return TelemetryServiceGrpc
                .newBlockingStub(telemetryChannel);
    }
}

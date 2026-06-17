package io.github.mshivaeifar.driverservice.infrastructure.grpc.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GrpcClientConfig {
    private GrpcProperties grpcProperties;

    @Bean
    public ManagedChannel telemetryChannel() {

        return ManagedChannelBuilder
                .forAddress(grpcProperties.host(), grpcProperties.port())
                .usePlaintext()
                .build();
    }
}

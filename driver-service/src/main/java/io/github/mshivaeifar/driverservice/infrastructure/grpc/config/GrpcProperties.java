package io.github.mshivaeifar.driverservice.infrastructure.grpc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "grpc.client")
public record GrpcProperties(String host, int port) {
}

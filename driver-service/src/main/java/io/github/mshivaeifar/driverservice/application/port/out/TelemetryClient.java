package io.github.mshivaeifar.driverservice.application.port.out;

import io.github.mshivaeifar.driverservice.application.dto.DriverLocation;
import io.github.mshivaeifar.driverservice.application.dto.FetchByUUIDRequest;

public interface TelemetryClient {

    DriverLocation getCurrentLocation(FetchByUUIDRequest driverId);

}

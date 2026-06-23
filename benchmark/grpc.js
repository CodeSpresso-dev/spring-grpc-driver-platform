import grpc from 'k6/net/grpc';
import { config } from './config.js';

export const options = {
    vus: config.vus,
    iterations: config.iterations,
};

const client = new grpc.Client();
client.load(['proto'], 'telemetry.proto');

// این متغیر برای هر VU به‌طور مستقل مقداردهی می‌شود
let connected = false;

export default function () {
    // هر VU فقط در اولین تکرار خود اتصال برقرار می‌کند
    if (!connected) {
        client.connect('localhost:12242', { plaintext: true });
        connected = true;
    }

    const response = client.invoke(
        'telemetry.TelemetryService/GetDriverLocation',
        { driverId: config.driverId }
    );

    // بررسی خطا (اختیاری اما مفید)
    if (response.error) {
        console.error(`Error: ${response.error.message}`);
    }
}
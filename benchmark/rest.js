import http from 'k6/http';
import { check } from 'k6';
import { config } from './config.js';

export const options = {
    vus: config.vus,
    iterations: config.iterations,
};

export default function () {

    const res = http.get(
        `http://localhost:8085/api/v1/drivers/${config.driverId}/location`
    );

    check(res, {
        'status is 200': r => r.status === 200,
    });
}
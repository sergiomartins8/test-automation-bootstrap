import http from 'k6/http';
import { group, check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

// Global failure rate
export let failureRate = new Rate('failures');

// Global options for the test
// Example: Ramp up to 500 virtual users within 30s, keep the load for another 60s and finally ramp down for 30s till zero virtual users
export let options = {
    stages: [
        {
            target: 500,
            duration: '30s'
        },
        {
            target: 500,
            duration: '60s'
        },
        {
            target: 0,
            duration: '30s'
        }
    ],
    thresholds: {
        // Test fails if the 95th percentile of requests exceed 500ms
        'http_req_duration{source:api}': ['p(95)<500'],

        failures: [
            // Global failed checks rate should be less than 5%
            'rate<0.05',
        ],
    },
};

// Just like a @BeforeTest annotation
// Here you'll setup the necessary steps to perform your test
// Example: Get access tokens to perform authenticated requests (AWS Cognito examplified here)
export function setup() {
    const body = JSON.stringify({
        'AuthParameters': {
            'USERNAME': `${__ENV.USER_EMAIL_ADDRESS}`, // ${USER_EMAIL_ADDRESS}
            'PASSWORD': `${__ENV.USER_PASSWORD}`, // ${USER_PASSWORD}
        },
        'AuthFlow': 'USER_PASSWORD_AUTH',
        'ClientId': `${__ENV.AWS_APP_CLIENT_ID}`, // ${AWS_APP_CLIENT_ID}
    });

    const headers = {
        'Content-Type': 'application/x-amz-json-1.1',
        'X-Amz-Target': 'AWSCognitoIdentityProviderService.InitiateAuth',
    };

    let response = http.post('https://cognito-idp.eu-west-1.amazonaws.com/', body, { headers: headers });
    return JSON.parse(response.body); // result to be used by the test
}

// Simulate user behavior navigating/checking stuff on the page
function simulateUserInteractionDelay() {
    sleep(1 + Math.random() * 2);
}

// The actual test
// Note that the parameter "data" is the return of the "setup" method
// Remove it if you don't use a "setup"
export default function (data) {
    group('Load testing', function () {
        // Use as many groups as you'd like
        group('My test name grouped by logic just for the sake of proper test structure', function () {
            let response = http.get('https://my.awesome.api.com/users', {
                tags: { source: 'api' },
                headers: {
                    authorization: data.AuthenticationResult.AccessToken,
                    identity: data.AuthenticationResult.IdToken
                }
            });

            const result = check(response, {
                'can list users': (r) => r.status === 200,
            });

            failureRate.add(!result);
        });

        simulateUserInteractionDelay();
    });
}

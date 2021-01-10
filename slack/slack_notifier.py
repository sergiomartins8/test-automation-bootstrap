#!/usr/bin/python3

import os
from slack_sdk import WebClient
from slack_sdk.errors import SlackApiError

token = os.getenv('SLACK_BOT_TOKEN')
environment = os.getenv('ENVIRONMENT')
pipeline_url = os.getenv('CI_PIPELINE_URL')
pipeline_status = os.getenv('CI_JOB_STATUS')


attachments_success = [
    {
        "mrkdwn_in": ["text"],
        "color": "#36a64f",
        "pretext": 'Pipeline results have just arrived and they were awesome :star:',
        "title": f"Results for the {environment.upper()} environment",
        "text": f"Check out the pipeline <{pipeline_url}|here>.",
        "footer": "This is an automated notification. Check the `e2e` repo for more details.",
    }
]

attachments_failure = [
    {
        "mrkdwn_in": ["text"],
        "color": "#DC143C",
        "pretext": 'Pipeline results have just arrived and they were burning :fire:',
        "title": f"Results for the {environment.upper()} environment",
        "text": f"Check out the pipeline <{pipeline_url}|here>.",
        "footer": "This is an automated notification. Check the `e2e` repo for more details.",
    }
]

try:
    attachments = attachments_success if pipeline_status == 'success' else attachments_failure
    client = WebClient(token=token)
    response = client.chat_postMessage(channel='<YOUR-CHANNEL-NAME-HERE>', attachments=attachments)
except SlackApiError as e:
    print(f"Got an error: {e.response['error']}")

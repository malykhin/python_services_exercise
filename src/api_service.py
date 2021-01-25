import time
import uuid
import boto3
from decouple import config

LOCALSTACK_SQS_ENDPOINT_URL = config("LOCALSTACK_SQS_ENDPOINT_URL")
BOOKING_QUEUE_URL = config("BOOKING_QUEUE_URL")

sqs = boto3.client("sqs", endpoint_url=LOCALSTACK_SQS_ENDPOINT_URL)

while (True):
    time.sleep(3)
    try:
        response = sqs.send_message(
            QueueUrl=BOOKING_QUEUE_URL,
            MessageBody="body",
            MessageDeduplicationId=str(uuid.uuid4()),
            MessageGroupId="messages",
            MessageAttributes={
                "contentType": {
                    "StringValue": "application/json", "DataType": "String"}
            }
        )
        print("posted")
    except:
        print("SQS send message failure")
        time.sleep(3)

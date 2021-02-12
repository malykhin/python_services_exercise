#!/usr/bin/env bash

readonly LOCALSTACK_SQS_URL=http://localstack:4566

sleep 5;

set -x

aws configure set aws_access_key_id foo
aws configure set aws_secret_access_key bar
echo "[default]" > ~/.aws/config
echo "region = us-east-1" >> ~/.aws/config
echo "output = json" >> ~/.aws/config


aws --endpoint-url $LOCALSTACK_SQS_URL sqs create-queue --queue-name reservation
aws --endpoint-url $LOCALSTACK_SQS_URL sqs create-queue --queue-name notification
aws --endpoint-url $LOCALSTACK_SQS_URL ses verify-email-identity --email-address altranjavademo2@gmail.com
aws --endpoint-url $LOCALSTACK_SQS_URL ses verify-email-identity --email-address altranjavademo1@gmail.com
aws --endpoint-url $LOCALSTACK_SQS_URL ses list-identities
echo "emails are verified"
aws --endpoint-url $LOCALSTACK_SQS_URL ses create-template --cli-input-json file:///docker-entrypoint-initaws.d/templates/successemail.json
aws --endpoint-url $LOCALSTACK_SQS_URL ses create-template --cli-input-json file:///docker-entrypoint-initaws.d/templates/erroremail.json
echo "email templates are created"
aws --endpoint-url $LOCALSTACK_SQS_URL ses list-templates

set +x
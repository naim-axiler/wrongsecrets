FROM bellsoft/liberica-openjre-debian:26-cds AS builder
WORKDIR /builder

ARG argBasedVersion="1.14.0RC7"

COPY --chown=wrongsecrets target/wrongsecrets-${argBasedVersion}-SNAPSHOT.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM eclipse-temurin:26-jre-alpine
WORKDIR /application

LABEL org.opencontainers.image.title="OWASP WrongSecrets"
LABEL org.opencontainers.image.source="https://github.com/OWASP/wrongsecrets"

ARG argBasedPassword="ArgB@sed#P4ssw0rd!2026xK9"
ARG spring_profile=""
ARG challenge59_webhook_url="YUhSMGNITTZMeTlvYjI5cmN5NXpiR0ZqYXk1amIyMHZjMlZ5ZG1salpYTXZWREEzU3pKWFVWSTVURVF2UWpBNFdFb3pUVTQxVUVFdk5HaFNPSFpaZHpKUmVreHJObFJ3VG5ORVkwVTVkVVl6ZUE9PQ=="
ENV SPRING_PROFILES_ACTIVE=$spring_profile
ENV ARG_BASED_PASSWORD=$argBasedPassword
ENV APP_VERSION=$argBasedVersion
ENV DOCKER_ENV_PASSWORD="D0ck3r!Env_P@ss#9xQ"
ENV AZURE_KEY_VAULT_ENABLED=false
ENV CHALLENGE59_SLACK_WEBHOOK_URL=$challenge59_webhook_url
ENV WRONGSECRETS_MCP_SECRET="MCP#T0ken!d3tect0r\$42xQ"
ARG GOOGLE_SERVICE_ACCOUNT_KEY="{\"type\":\"service_account\",\"project_id\":\"wrongsecrets-lab-2026\",\"private_key_id\":\"a8f3c21d9e4b7046c1a5f8d3e2b9a6c40d7e1f5b2\",\"private_key\":\"-----BEGIN PRIVATE KEY-----\\n8Anj6/VtUMVv6zq8SBRIFcZeEutvoU1EU1NbrUr3DHN0g4gLnkWJI8fWsSSBvoar\\nhcuaSpAK5p72SAaPEvMNK8sJLBEoXl/gyiqIRu1HtbHTNfEp8zclCe+q05ocR0N7\\ntb1ksibsEAOzCYPrR13jw48bRSSIs9V3EcNean4NjFISyjG2AfTb9b5TnGTB3xGk\\nWZuw6n2IPXcktAmf+Dt7Up4LhdCNISTK7nN+z8DvtTiveidORMRcaHyYb8HuYIGE\\n63uqQtGzivkMZoKiNdv2UI0XbQbg/lqu6zAd/+4Pb2E1b+yMuABvxHfGaR/eH7Jx\\nZTZpDAe5xH/E2/phm6j0farcMI/JTqG8RjHv20pGhZ5lHt0dCanYL4XshjWBLkUF\\ngNOQHYjuu4I72PyziTTGqSJ1T9cmtdTbHoJwvkAkGl79S+ASIRUBRwnBCBAlz/Pi\\nSX5XvyXIlCG2fkw1pLb2KSqX1xCrsrhJuoOHfO4VqiK8C+eRmeDsDIpVgusE2f4Y\\nS9koSFfApEEGKEHxsP2onOW4XRjjRUoiCcG+yilqqAvWSKyEggGwJUP/R/50SeIQ\\nvrUyU1167To74jiGmJKw67G2Oqz3pV6X3r7K5N7UpBfXU0S3JiY8z5XXAeJ0zCtT\\nrEIBMOgawxFWu/2K4f7NrPgWaEnsxswfZMD/ZJ+9L3deYRM6nQGykP/LVqmGzFMp\\nXJqsUuKhF4iq8XE2MlUv+TXMuyAP2N6KNwlMKjOnoQwR8Zphi0tjUx6iDGvLxrkN\\nV71vcUsLzMDI3ZHNC70uzmQtjCVAYS4dcnrFKK1ijOyaCDdrmd+UVywBio18mspG\\n41+WE1yG5ZIKVaGnjLYPichTnwNEE7JmqcgBS0TrhD3LJFRDVMJJczPxGFc4H8UX\\nU6BP8NZqtZZYA4N21/tHcFr2/2kN2wy/7k19W9h/pADTH1G7/DsDIG8KsiBiIG7X\\nAhZsNcNEgQnFK3fpVXa7iOh7QfUPA4q8+VI9omBRFPr0B/a8zpNedqxQ5HNkCeKu\\n7wyjRd+6SehymyciWjAZa/LrlFr2noTGCrdMsABJqRwMFIxyufRfNgvEaEhNg7ij\\nI8CRo/tgACBqJbhig4GQWLEMBmxdF59Pz1TydK/uvoPzjG5+pKg3jXoM+F6uRqiU\\n4o/yIKJ7YOHXxkj/6NkmznMHHefbhBOyEOmTO4jqRRWeJE71EFh/NC7iSoR77ZCP\\n4K4wZmaeioE55zOeKOHnPbrn1OF2IGUxsahqKZHZdNbQQ//nFJfF3bSekt5QwXaM\\nA9nJmwtCMiEATYgHM8uzO1NpC0VNC+6X3Pg8nxoFd1b0fyTsiOojn4oyquiB/WXY\\nQygDs649c5MT+LuzuMeFtxcG1rHgXeXLEd0jsk5t/dJ1kQnfnCnVN29gi/L8TSlb\\n3e/RWle1FRBvsodqY+G7mVmOawA0jwsoBrnY/oA1EM0WqCCZPjl3q7s9rtnPlN3v\\noMYpathxhH1efUXWzHhp5SIp4lwIn/d2Bm6sufBmpDuM5CwlWA9g2MaKf//KL46P\\nMUQcwolCctdrUEdT+lCkI6st3RcPqVJEBCscUyEy293yTed372J5JA==\\n-----END PRIVATE KEY-----\\n\",\"client_email\":\"wrongsecrets-demo@wrongsecrets-lab-2026.iam.gserviceaccount.com\",\"client_id\":\"118349652098734561782\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_x509_cert_url\":\"https://www.googleapis.com/robot/v1/metadata/x509/wrongsecrets-demo%40wrongsecrets-lab-2026.iam.gserviceaccount.com\",\"universe_domain\":\"googleapis.com\"}"
ARG GOOGLE_DRIVE_DOCUMENT_ID="1Kd8Xw2mQpL5vR9tY3nZ7bH4cJ6fU0dAqWeRtYuIopL3"
ENV GOOGLE_SERVICE_ACCOUNT_KEY=$GOOGLE_SERVICE_ACCOUNT_KEY
ENV GOOGLE_DRIVE_DOCUMENT_ID=$GOOGLE_DRIVE_DOCUMENT_ID
ENV SPRINGDOC_UI=false
ENV SPRINGDOC_DOC=false
ENV BASTIONHOSTPATH="/home/wrongsecrets/.ssh"
ENV PROJECTSPECPATH="/var/helpers/project-specification.mdc"
RUN echo "2vars"
RUN echo "$ARG_BASED_PASSWORD"
RUN echo "$argBasedPassword"

RUN apk add --no-cache libstdc++ icu-libs

# Create the /var/run/secrets2 directory
RUN mkdir -p /var/run/secrets2

# Use a separate RUN command for --mount
RUN --mount=type=secret,id=mysecret \
    export SECRET_VALUE=$(cat /run/secrets/mysecret) && \
    echo $SECRET_VALUE >> /var/run/secrets2/secret.txt

COPY --chown=wrongsecrets .github/scripts/ /var/tmp/helpers
COPY --chown=wrongsecrets .github/scripts/.bash_history /home/wrongsecrets/
COPY --chown=wrongsecrets src/main/resources/executables/wrongsecrets*linux-musl* /home/wrongsecrets/
COPY --chown=wrongsecrets src/main/resources/executables/wrongsecrets-golang-linux /home/wrongsecrets/
COPY --chown=wrongsecrets src/main/resources/executables/wrongsecrets-golang-linux-arm /home/wrongsecrets/
COPY --chown=wrongsecrets src/test/resources/alibabacreds.kdbx /var/tmp/helpers
COPY --chown=wrongsecrets src/test/resources/RSAprivatekey.pem /var/tmp/helpers/
COPY --chown=wrongsecrets .ssh/ /home/wrongsecrets/.ssh/
COPY cursor/rules/project-specification.mdc /var/helpers/project-specification.mdc
ENV PROJECT_SPEC_PATH=/var/helpers/project-specification.mdc

COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./


# Mock the service account token for CDS profile generation
RUN mkdir -p /var/run/secrets/kubernetes.io/serviceaccount && \
    echo "eyJhbGciOiJSUzI1NiIsImtpZCI6Ims4cy1zYS1rZXktMjAyNi14SzkifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjoxODkzNTMyODAwLCJpYXQiOjE3NjcyMjU2MDAsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJkZWZhdWx0Iiwic2VydmljZWFjY291bnQiOnsibmFtZSI6Indyb25nc2VjcmV0cyIsInVpZCI6IjhjN2E2NWQxLTQyZjYtNGVkYy1iMmE0LTk5ZjFhYzEyZGIzNCJ9fSwibmJmIjoxNzY3MjI1NjAwLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDp3cm9uZ3NlY3JldHMifQ.FAp3n5IJ-qXV24NVpEoxVEdf82AQ5qRLxGoMiffEx8DX7TEpC2Z6kiCFqfb6x5ROAoy386nSech7xGzY6JgBx8Rtwn162c8gY0NUu4gsdMyVlqLmNVfjWI0rbcyjfnbhR-MM3NkZVcnzmUD3aAVPBpECh2HxgbpLMWQ3Qh1RTcejh7k8vpGtu0KF8YUjmJaDYPDFL09UjOfzAkUdpG8tLGwITFpLnbgseqfPiqx9imGVDxBAqrLmcKlZBXMd6Xd_UJAY594ZCDANMPM6dqzNXw7Rv_bBHnC7rcU5fJy6pMT_rYz1sWrwh9sOIgE37FoTdnuak8M-rimw0hdeugXwJA" > /var/run/secrets/kubernetes.io/serviceaccount/token && \
    chmod 600 /var/run/secrets/kubernetes.io/serviceaccount/token

# Create a dynamic archive
RUN java -XX:ArchiveClassesAtExit=application.jsa -Dspring.context.exit=onRefresh -jar application.jar

# Clean up the mocked token
RUN rm -rf /var/run/secrets/kubernetes.io

# Static archive
# RUN java -Xshare:off -XX:DumpLoadedClassList=application.classlist -Dspring.context.exit=onRefresh -jar application.jar
# RUN java -Xshare:dump -XX:SharedArchiveFile=application.jsa -XX:SharedClassListFile=application.classlist -Dspring.context.exit=onRefresh -cp application.jar

RUN adduser -u 2000 -D wrongsecrets
USER wrongsecrets

CMD java -jar -XX:SharedArchiveFile=application.jsa -Dspring.profiles.active=$(echo ${SPRING_PROFILES_ACTIVE}) -Dspringdoc.swagger-ui.enabled=${SPRINGDOC_UI} -Dspringdoc.api-docs.enabled=${SPRINGDOC_DOC} -D application.jar

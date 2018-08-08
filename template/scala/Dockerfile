# Pull base image from https://github.com/hseeberger/scala-sbt
FROM hseeberger/scala-sbt

# 1. Use any image as your base image, or "scratch"
# 2. Add fwatchdog binary via https://github.com/openfaas/faas/releases/
# 3. Then set fprocess to the process you want to invoke per request - i.e. "cat" or "my_binary"

#ADD https://github.com/openfaas/faas/releases/download/0.8.9/fwatchdog /usr/bin
#RUN chmod +x /usr/bin/fwatchdog

RUN echo "Pulling watchdog binary from Github." \
    && curl -sSL https://github.com/openfaas/faas/releases/download/0.8.9/fwatchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && cp /usr/bin/fwatchdog /home/app

# Function
COPY function function/
WORKDIR /root/function/

RUN echo "Copied Sources" && ls

RUN echo "Building with SBT"
RUN sbt clean assembly

# Populate example here - i.e. "cat", "sha512sum" or "node index.js"
ENV fprocess="java -jar handler.jar"

# Set to true to see request in function logs
ENV write_debug="true"

HEALTHCHECK --interval=5s CMD [ -e /tmp/.lock ] || exit 1
CMD [ "fwatchdog" ]

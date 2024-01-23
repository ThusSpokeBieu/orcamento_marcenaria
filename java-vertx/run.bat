@echo off
java -Xms2G -Xmx2G -server -XX:+UseNUMA -XX:+UseZGC ^
    -Dvertx.disableMetrics=true -Dvertx.disableH2c=true ^
    -Dvertx.disableWebsockets=true -Dvertx.flashPolicyHandler=false ^
    -Dvertx.threadChecks=false -Dvertx.disableContextTimings=true ^
    -Dvertx.disableTCCL=true -Dvertx.disableHttpHeadersValidation=true ^
    -Dio.netty.buffer.checkBounds=false -Dio.netty.buffer.checkAccessible=false ^
    -cp target/java-vertx-1.0-SNAPSHOT.jar github.gmess.App

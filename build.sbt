name := "CloudPubSubDemo"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies += "com.google.cloud" % "google-cloud-pubsub" % "1.108.1"

// https://mvnrepository.com/artifact/com.google.cloud/google-cloud-shared-dependencies
libraryDependencies += "com.google.cloud" % "google-cloud-shared-dependencies" % "0.8.6" pomOnly()
// https://mvnrepository.com/artifact/com.google.api.grpc/grpc-google-cloud-pubsub-v1
libraryDependencies += "com.google.api.grpc" % "grpc-google-cloud-pubsub-v1" % "1.90.1"
// https://mvnrepository.com/artifact/com.google.api.grpc/proto-google-cloud-pubsub-v1
libraryDependencies += "com.google.api.grpc" % "proto-google-cloud-pubsub-v1" % "1.90.1"

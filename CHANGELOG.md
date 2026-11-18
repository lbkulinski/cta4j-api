# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.10] - 2026-11-17

### Security

- The base image `amazoncorretto:25-alpine3.24` now ships Corretto 25.0.5.1.2, which fixes CVE-2026-114938 ("Java4Shell") and the other CVEs in the [November 2026 OpenJDK advisory](https://openjdk.org/groups/vulnerability/advisories/2026-11-17).

### Changed

- Bump `org.springframework.boot:spring-boot-starter-parent` from `4.1.1` to `4.1.2`.
- Bump `org.springdoc:springdoc-openapi-starter-webmvc-ui` from `3.0.3` to `3.2.0`.
- Bump `org.apache.maven:apache-maven` from `3.9.16` to `3.10.0`.

### Removed

- The `-XX:-UseBASE64Intrinsics` JVM flag added in 1.0.9, since the patched JDK makes it unnecessary.

## [1.0.9] - 2026-11-17

### Security

- Mitigate CVE-2026-114938 ("Java4Shell"), a memory-corruption flaw in the HotSpot x86-64 Base64 decoder intrinsic, by starting the JVM with `-XX:-UseBASE64Intrinsics`. Rebuild the image on a patched Corretto 25 release (25.0.5.1.2 or later) and then consider removing the flag.

### Changed

- Update the Docker base image from `amazoncorretto:25-alpine3.19` to `amazoncorretto:25-alpine3.24`, since Alpine 3.19 is no longer supported.
- Bump `org.springframework.boot:spring-boot-starter-parent` from `4.0.4` to `4.1.1`.
- Bump `org.projectlombok:lombok` from `1.18.44` to `1.18.48`.
- Bump `com.rollbar:rollbar-java` from `2.1.0` to `2.3.1`.
- Bump `com.github.ben-manes.caffeine:caffeine` from `3.2.3` to `3.2.4`.
- Bump `org.springdoc:springdoc-openapi-starter-webmvc-ui` from `3.0.2` to `3.0.3`.
- Bump `software.amazon.awssdk:bom` from `2.42.18` to `2.54.12`.
- Bump `org.apache.maven:apache-maven` from `3.9.7` to `3.9.16`.

## [1.0.8] - 2026-04-06

### Changed

- Bump `org.springframework.boot:spring-boot-starter-parent` from `4.0.3` to `4.0.4`.
- Bump `org.projectlombok:lombok` from `1.18.42` to `1.18.44`.
- Bump `org.springdoc:springdoc-openapi-starter-webmvc-ui` from `3.0.1` to `3.0.2`.
- Bump `software.amazon.awssdk:bom` from `2.41.34` to `2.42.18`.

## [1.0.7] - 2025-02-26

### Changed

- Changed CORS configuration to use configuration properties instead of hardcoding them.
- Changed CORS configuration to allow requests from `https://ctasmokers.com`, `https://www.ctasmokers.com`.
- Changed `org.springframework.boot:spring-boot-starter-web` to `org.springframework.boot:spring-boot-starter-webmvc`.
- Bump `org.springframework.boot:spring-boot-starter-parent` from `4.0.0` to `4.0.3`.
- Bump `com.rollbar:rollbar-java` from `2.0.0` to `2.1.0`.
- Bump `com.cta4j:cta4j-java-sdk` from `3.0.3` to `3.0.4`.
- Bump `org.springdoc:springdoc-openapi-starter-webmvc-ui` from `3.0.0` to `3.0.1`.
- Bump `software.amazon.awssdk:bom` from `2.39.2` to `2.41.34`.

## [1.0.6] - 2025-11-28

### Changed

- Bump `com.cta4j:cta4j-java-sdk` from `3.0.2` to `3.0.3`.

## [1.0.5] - 2025-11-28

### Changed

- Bump `com.cta4j:cta4j-java-sdk` from `3.0.1` to `3.0.2`.

## [1.0.4] - 2025-11-28

### Changed

- Fixed LICENSE file copyright.
- Bump `org.springframework.boot:spring-boot-starter-parent` from `3.5.7` to `4.0.0`.
- Bump `org.springdoc:springdoc-openapi-starter-webmvc-ui` from `2.8.14` to `3.0.0`.
- Bump `com.cta4j:cta4j-java-sdk` from `3.0.0` to `3.0.1`.
- Bump `software.amazon.awssdk:bom` from `2.38.2` to `2.39.2`.

### Removed

- Removed `org.apache.commons:commons-lang3` dependency.

## [1.0.3] - 2025-11-13

### Changed

- Bump `org.springframework.boot:spring-boot-starter-parent` from `3.5.6` to `3.5.7`.
- Bump `com.github.ben-manes.caffeine:caffeine` from `3.2.2` to `3.2.3`.
- Bump `org.springdoc:springdoc-openapi-starter-webmvc-ui` from `2.8.13` to `2.8.14`.
- Bump `software.amazon.awssdk:bom` from `2.35.10` to `2.38.2`.

### Removed

- Removed `ch.qos.logback:logback-core` dependency.

## [1.0.2] - 2025-10-26

### Changed

- Separated GitHub Actions workflows for build and release.

## [1.0.1] - 2025-10-26

### Removed

- Java options from Dockerfile.

## 1.0.0 - 2025-10-26

### Added

- Initial release of API.

[Unreleased]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.10...HEAD
[1.0.10]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.9...v1.0.10
[1.0.9]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.8...v1.0.9
[1.0.8]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.7...v1.0.8
[1.0.7]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.6...v1.0.7
[1.0.6]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.5...v1.0.6
[1.0.5]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.4...v1.0.5
[1.0.4]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.3...v1.0.4
[1.0.3]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.2...v1.0.3
[1.0.2]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/lbkulinski/cta4j-api/compare/v1.0.0...v1.0.1

# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/).

## [1.0.0] - 18.12.2017
### Added
- Lippu API 1.0 implementation.
- JWT authentication tokens, JWE and JWS supported.
- Authentication implementation using Spring security.
- Simple background services, no journeyplanner, routes
  with multiple stops, complex timetables. Only one
  departure per day per route.
- Integration tests.
- Ticket service generates STUB ticket payloads.
- License (EUPL v1.2) and LICENSE.txt
- Data storage with Spring data key-value maps.
- Test data loading using Jackson2RepositoryPopulatorFactoryBean.
- Logback configuration.
- Requests logging with CommonsRequestLoggingFilter
- Docker definitions for building in container and running
  Lippu-API service in a container

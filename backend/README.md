# TaskCrafter Backend

## Run locally

This backend application includes a preconfigured local setup. All you have to do is starting up the local docker compose:

```bash
docker compose -f docker-compose-local.yml up -d
```

and starting the spring boot application with the `local` profile.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```
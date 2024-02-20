.ONESHELL: # Applies to every targets in the file!

.PHONY: help
help:
	@printf "────────────────────────`tput bold``tput setaf 2` Make Commands `tput sgr0`────────────────────────────────\n"
	@sed -ne "s/^##\(.*\)/\1/p" $(MAKEFILE_LIST)
	@sed -ne "/@sed/!s/\(^[^#?=]*:\).*##\(.*\)/`tput setaf 2``tput bold`\1`tput sgr0`\2/p" $(MAKEFILE_LIST)
	@printf "───────────────────────────────────────────────────────────────────────\n"

.PHONY: playground-start
playground-start: ## starts a playground docker environment that's required for the application to run
	$(MAKE) playground-stop
	docker-compose -f ./infra/docker-compose.yml up -d

.PHONY: playground-stop
playground-stop: ## stops the last playground docker environment if available
	docker-compose -f ./infra/docker-compose.yml down -v


.PHONY: app-build
app-build: ## builds and tests the application
	./mvnw clean install

.PHONY: app-docker-image
app-docker-image: ## builds, tests and packages the application as a runnable docker image
	$(MAKE) app-build
	docker build -t example/demo .

.PHONY: app-docker-run
app-docker-run: ## runs the dockerized application in the playground environment under the port 8080
	docker run -it -e SPRING_PROFILES_ACTIVE='docker' -p 8080:8080 --network demo-network example/demo:latest


.PHONY: app-playground
app-playground: ## starts the docker playground, builds, tests and dockerizes the app and runs it then as docker image in the docker playground
	$(MAKE) playground-start
	$(MAKE) app-docker-image
	$(MAKE) app-docker-run
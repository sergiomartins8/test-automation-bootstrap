.DEFAULT_GOAL:=help

COMPOSE_SETUP_FILE := -f docker-compose.setup.yml
COMPOSE_FILE := -f docker-compose.yml

ELK_MAIN_SERVICES   := elasticsearch logstash kibana
ELK_MONITORING := heartbeat

ELK_ALL_SERVICES := ${ELK_MAIN_SERVICES} ${ELK_MONITORING}
# --------------------------

# load .env so that Docker Swarm Commands has .env values too. (https://github.com/moby/moby/issues/29133)
include .env
export

# --------------------------
.PHONY: setup keystore certs all elk monitoring tools build down stop restart rm logs

keystore:		## Setup Elasticsearch Keystore, by initializing passwords, and add credentials defined in `keystore.sh`.
	docker-compose ${COMPOSE_SETUP_FILE} run --rm keystore

certs:		    ## Generate Elasticsearch SSL Certs.
	docker-compose ${COMPOSE_SETUP_FILE} run --rm certs

setup:		    ## Generate Elasticsearch SSL Certs and Keystore.
	@make certs
	@make keystore

elk:		    ## Start ELK.
	docker-compose ${COMPOSE_FILE} up -d --build ${ELK_MAIN_SERVICES}

monitoring:		## Start ELK Monitoring.
	@docker-compose ${COMPOSE_FILE} up -d --build ${ELK_MONITORING}

build:			## Build ELK and all its extra components.
	@docker-compose ${COMPOSE_FILE} build ${ELK_ALL_SERVICES}

down:			## Down ELK and all its extra components.
	@docker-compose ${COMPOSE_FILE} down ${ELK_ALL_SERVICES}

stop:			## Stop ELK and all its extra components.
	@docker-compose ${COMPOSE_FILE} stop ${ELK_ALL_SERVICES}

restart:		## Restart ELK and all its extra components.
	@docker-compose ${COMPOSE_FILE} restart ${ELK_ALL_SERVICES}

rm:				## Remove ELK and all its extra components containers.
	@docker-compose $(COMPOSE_FILE) rm -f ${ELK_ALL_SERVICES}

logs:			## Tail all logs with -n 1000.
	@docker-compose $(COMPOSE_FILE) logs --follow --tail=1000 ${ELK_ALL_SERVICES}

images:			## Show all Images of ELK and all its extra components.
	@docker-compose $(COMPOSE_FILE) images ${ELK_ALL_SERVICES}

prune:			## Remove ELK Containers and Delete Volume Data
	@make stop && make rm
	@docker volume prune -f

help:       	## Show this help.
	@echo "Make Application Docker Images and Containers using Docker-Compose files in 'docker' Dir."
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m (default: help)\n\nTargets:\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-12s\033[0m %s\n", $$1, $$2 }' $(MAKEFILE_LIST)

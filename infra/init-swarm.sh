#!/bin/bash
set -e
ADVERTISE_ADDR=$(hostname -I | awk '{print $1}')
docker swarm init --advertise-addr ${ADVERTISE_ADDR}
echo "Manager initialized at ${ADVERTISE_ADDR}"
docker swarm join-token worker -q
docker swarm join-token manager -q

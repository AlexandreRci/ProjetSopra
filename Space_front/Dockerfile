FROM node:lts-bullseye AS build

WORKDIR /app

COPY package*.json .

RUN npm install

COPY . .

RUN ./node_modules/.bin/ng build

FROM nginx:latest

WORKDIR /app

# Pas de COPY nginx.conf -> on garde la config par défaut
# COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build /app/dist/space_front/ /usr/share/nginx/html/

EXPOSE 80

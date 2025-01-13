# Build Vue frontend
FROM node:18-alpine as build-stage

WORKDIR /app
COPY ./warehouse-front/package*.json ./
RUN yarn install
COPY ./warehouse-front/ ./
RUN yarn run build

# Serve frontend with NGINX
FROM nginx:alpine
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

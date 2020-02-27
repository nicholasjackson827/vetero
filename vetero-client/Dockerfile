# Sample Dockerfile taken from
# https://medium.com/greedygame-engineering/so-you-want-to-dockerize-your-react-app-64fbbb74c217

FROM nginx:alpine
COPY /build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
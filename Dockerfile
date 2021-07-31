FROM nginx:alpine
COPY dist /usr/share/nginx/html
# fix Angular web refresh 404 Error issue
COPY default.conf /etc/nginx/conf.d

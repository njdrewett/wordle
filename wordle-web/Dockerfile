FROM node:16

# Create app directory
WORKDIR /opt/wordle-web

# Install app dependencies
COPY package*.json ./

#RUN npm install
# If you are building your code for production
RUN npm ci --only=production

RUN mkdir -p /opt/wordle-web/config
RUN mkdir -p /opt/wordle-web/app
RUN mkdir -p /opt/wordle-web/static

# Bundle app source
COPY ./target/wordle-web/ /opt/wordle-web/

EXPOSE 8081
ENV NODE_ENV=production
CMD [ "node", "app/wordle-app.js" ]
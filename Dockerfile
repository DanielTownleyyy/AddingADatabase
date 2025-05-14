# Setup for simple Node.js Application!!!

# Use Node.js base image
FROM node:16

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the app
COPY . .

# Expose port (adjust if different)
EXPOSE 3000

# Run the app
CMD ["node", "server.js"]


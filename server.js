// Import required libraries
const express = require('express');         // Web framework to build the server
const mongoose = require('mongoose');       // Library to connect to MongoDB
const bodyParser = require('body-parser');  // Middleware to handle JSON body data

// Create the Express app
const app = express();

// Define the port number the server will run on
const port = 3000;

// Use body-parser to read JSON data from incoming requests
app.use(bodyParser.json());

// MongoDB connection string (in Docker, "mongo" is the hostname of the container)
const mongoURI = 'mongodb://mongo:27017/mydatabase';

// Connect to MongoDB using Mongoose
mongoose.connect(mongoURI, {
  useNewUrlParser: true,        // Use the new URL parser
  useUnifiedTopology: true,     // Use the new connection engine
});

// Listen for errors or successful connection
const db = mongoose.connection;
db.on('error', (err) => console.error('MongoDB connection error:', err));
db.once('open', () => console.log('Connected to MongoDB'));

// Define a schema (structure) for our data
const ItemSchema = new mongoose.Schema({
  name: String,                     // Each item will have a "name" field
  createdAt: {
    type: Date,                     // Automatically adds the date it was created
    default: Date.now
  }
});

// Create a model from the schema
const Item = mongoose.model('Item', ItemSchema);

// A test route to check if the server is running
app.get('/', (req, res) => {
  res.send('🚀 Hello from Dockerized Node.js app with MongoDB!');
});

// POST route to add new items to the database
app.post('/data', async (req, res) => {
  try {
    const item = new Item({ name: req.body.name }); // Create a new item from the request body
    const saved = await item.save();                // Save it to the database
    res.status(201).json(saved);                    // Send back the saved item
  } catch (err) {
    res.status(500).json({ error: 'Failed to save data' }); // Send error if it fails
  }
});

// GET route to fetch all items from the database
app.get('/data', async (req, res) => {
  try {
    const items = await Item.find();    // Find all items in the collection
    res.json(items);                    // Send back the items as JSON
  } catch (err) {
    res.status(500).json({ error: 'Failed to fetch data' }); // Send error if it fails
  }
});

// Start the server and listen on the defined port
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});

WebSocket Chat App

This Java-based WebSocket chat application provides a real-time messaging platform for users to communicate with each other. The app utilizes Spring Boot and WebSocket to establish bi-directional communication channels between clients and the server.

Key Features

Real-time messaging: Send and receive messages instantly, with no need for page refreshes or polling.
Group chat functionality: Users can participate in multiple chat rooms and engage with others in real-time.
Secure connections: WebSocket connections are established over a single TCP connection, ensuring secure data transmission.
Technical Requirements

Java 11 or higher
Spring Boot 2.3.0 or higher
WebSocket API
How it Works

Clients (e.g., web browsers) establish WebSocket connections to the server using the WebSocket API.
Once connected, clients can send and receive messages to/from the server.
The server, running Spring Boot, handles incoming messages and broadcasts them to relevant clients.
Clients receive messages and update the chat interface accordingly.
Code Structure

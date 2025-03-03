# Connection Pool Requirements
## Basic Connection Acquisition and Release
### Acquire a Connection:
Provide a method that returns a connection from the pool. If one is available, return it immediately.
### Release a Connection:
Implement a method to return a connection back to the pool once it's no longer needed.

## Maximum Pool Size
Define a maximum number of connections that can be created.
Use a thread-safe data structure, such as a BlockingQueue (e.g., LinkedBlockingQueue), to hold idle connections.

## Thread-Safety
Ensure that operations on the pool (acquiring, releasing, creating connections) are thread-safe.
Leverage built-in concurrency mechanisms provided by Java (like the atomic operations available in a BlockingQueue) to avoid manual synchronization wherever possible.

## Basic Connection Validation
Before handing out a connection, check if it is valid (e.g., not closed).
If a connection is invalid, remove it from the pool and, if under the maximum limit, create a new connection.

## Simple Timeout/Error Handling
If no connection is available and the maximum pool size has been reached, wait for a configurable timeout.
If a connection isn’t acquired within that timeout, throw a clear exception.
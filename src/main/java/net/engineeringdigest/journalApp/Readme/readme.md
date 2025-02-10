# HTTP Status Codes

## 1XX (Informational)

These codes indicate that the server has received the request and is continuing the process.

---

## 2XX (Successful)

These codes indicate that the request was successfully received, understood, and accepted.

- **200 OK** - The request was successfully processed.
- **201 Created** - A new resource has been successfully created.
- **204 No Content** - The request was successfully processed, but no content is returned.

---

## 3XX (Redirection)

These codes indicate that further action needs to be taken by the client to complete the request.

- **301 Moved Permanently** - The requested resource has been permanently moved to a new location.
- **302 Found** - The requested resource resides temporarily at a different URI.
- **304 Not Modified** - The resource has not been modified since the last request.

---

## 4XX (Client Errors)

These codes indicate errors caused by the client.

- **400 Bad Request** - The server could not understand the request due to invalid syntax.
- **401 Unauthorized** - The request requires user authentication.

---

## 5XX (Server Errors)

These codes indicate that the server failed to fulfill a valid request.

- **500 Internal Server Error** - A generic error message for unexpected server conditions.
- **502 Bad Gateway** - The server received an invalid response from the upstream server.
- **503 Service Unavailable** - The server is temporarily unable to handle the request (e.g., overloaded or down for maintenance).

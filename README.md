# Bitespeed Backend Task: Identity Reconciliation

## Project Description

This project involves creating a web service that consolidates customer contact information for e-commerce. The service helps identify and link different orders made with various contact details (emails and phone numbers) to the same individual, providing a unified customer profile for a personalized experience.

## Technology used

- **SpringBoot** (backend framework)
- **DataBase** - MY SQL to store and manage contact information
- **Database Hosted** - Railway Platform
  -**Spring Application**- Application Hosted in Render cloud to build and run app auto deploys from Git.

## Endpoint Specification
**https://identityreconcileplugin.onrender.com/identify**
(Please note: Due to server optimization settings, the initial request to the endpoint after a period of inactivity may experience a brief delay. Subsequent requests will respond more quickly.)

### Endpoint: `/identify`

#### Method: POST

#### Request Body

The request should be a JSON object containing the following fields:
- `email` (optional): A string representing the customer's email address.
- `phoneNumber` (optional): A number representing the customer's phone number.

Example:
```json
{
  "email": "docbrown@example.com",
  "phoneNumber": 1234567890
}
```

#### Response
The response will be a JSON object containing the consolidated contact information.
```json
{
  "contact": {
    "primaryContactId": 1,
    "emails": ["docbrown@example.com"],
    "phoneNumbers": [1234567890],
    "secondaryContactIds": [2, 3]
  }
}
```
#### API Testing

Use tools like Postman or Curl to test the /identify endpoint.Send POST requests with different email and phone number combinations to verify the consolidation logic.




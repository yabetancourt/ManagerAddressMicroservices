Some requests

**Creating an Address**

```bash
curl --location 'http://localhost:8072/address-service/address' \
--header 'Content-Type: application/json' \
--data '{
  "direction": "Calle J entre A y B # 3",
  "enabled": true
}'
```

**Getting an Address**

```bash
curl --location 'http://localhost:8072/address-service/address/1'
```

**Creating a Center Authorization**

```bash
curl --location 'http://localhost:8072/manager-service/center-authorization' \
--header 'Content-Type: application/json' \
--data '{
  "authorizationNumber": "YBM010801"
}'
```

**Creating a Manager**

```bash
curl --location 'http://localhost:8072/manager-service/manager' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Yadier",
  "nif": "X12345678",
  "addressId": 1,
  "enabled": true,
  "authorizationIds": [
    1,2
  ]
}'
```

**Getting a Manager**

```bash
curl --location 'http://localhost:8072/manager-service/manager/1'
```

**Updating a Manager**

```bash
curl --location --request PUT 'http://localhost:8072/manager-service/manager/1' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Ana",
  "nif": "Y00011111",
  "addressId": 2,
  "enabled": true,
  "authorizationIds": [
    1
  ]
}'
```

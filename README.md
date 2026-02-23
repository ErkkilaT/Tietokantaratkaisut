# Online store database API
## Products
### Get product information
- **Endpoint**: `/products/category/{id}`
- **Endpoint**: `/products/product/{id}`
- **Endpoint**: `/products/`
- **Method**: GET
- **Description**: Retrieve product information: category retrieves all products from `category` by ID and `product` retrieves single item, none retrieves all products
- **Parameters**:
  - `id` (int) - product or category ID number
  - `none` - all products
- **Response**:JSON format

### Update product information
- **Endpoint**: `/products/product/{id}`
- **Method**: PUT
- **Description**: Edit product
- **Parameters**:
  - `id` (int) - product id
  - TODO: add PUT information
### Add new product
- **Endpoint**: `/products/product/`
- **Method**: POST
- **Description**: Add product
- **Parameters**:
  - TODO: add POST information
### Remove product
- **Endpoint**: `/products/product/{id}`
- **Method**: DELETE
- **Description**: Remove product
- **Parameters**:
  - `id` (int) - product id

## Customers
### Get customer information
- **Endpoint**: `/user/{id}`
- **Method**: GET
- **Description**: Retrieve user information
- **Parameters**:
  - `id` (int) - customer id
  - `none` - all users
- **Response**:JSON format
### Update customer information
- **Endpoint**: `/user/{id}`
- **Method**: PUT
- **Description**: Edit user information
- **Parameters**:
  - `id` (int) - customer id
  - TODO: add PUT information
### Register new customer
- **Endpoint**: `/user/`
- **Method**: POST
- **Description**: Register new user
- **Parameters**:
  - TODO: add POST information
### Remove customer
- **Endpoint**: `/user/{id}`
- **Method**: DELETE
- **Description**: Remove customer information
- **Parameters**:
  - `id` (int) - customer id

## Orders
### Get order information by id
- **Endpoint**: `/order/{id}`
- **Method**: GET
- **Description**: Retrieve order information, including all products ordered
- **Parameters**:
  - `id` (int) - order id
- **Response**:JSON format
### Get order information by customer id
- **Endpoint**: `/order/customer/{id}`
- **Method**: GET
- **Description**: Retrieve order information, including all products ordered for single customer
- **Parameters**:
  - `id` (int) - customer id
- **Response**:JSON format
### Get order information by status
- **Endpoint**: `/order/{status}`
- **Method**: GET
- **Description**: Retrieve information on all orders with specific status
- **Parameters**:
  - `status` (string) - options: `CANCELLED` or `NEW` or `SHIPPED`
- **Response**:JSON format
### Update order information
- **Endpoint**: `/order/{id}`
- **Method**: PUT
- **Description**: Edit order information
- **Parameters**:
  - `id` (int) - order id
  - TODO: add PUT information
### Add new order
- **Endpoint**: `/order/{id}`
- **Method**: POST
- **Description**: Add new order
- **Parameters**:
  - TODO: add POST information
### Remove order
- **Endpoint**: `/order/{id}`
- **Method**: DELETE
- **Description**: Remove order information
- **Parameters**:
  - `id` (int) - customer id


# Restful-booker

**Source:** https://restful-booker.herokuapp.com/apidoc/index.html
**Saved:** 2026-08-30T09:43:09.122Z

*Generated with [markdown-printer](https://github.com/levz0r/markdown-printer) (v1.2.0) by [Lev Gelfenbuim](https://lev.engineer)*

---

# Auth - CreateToken

-   [сравнить с:](#)
-   [1.0.0](#)

Creates a new auth token to use for access to the PUT and DELETE /booking

post

```http
https://restful-booker.herokuapp.com/auth
```

-   [Example 1:](#examples-Auth-CreateToken-1_0_0-0)

```json
curl -X POST \
  https://restful-booker.herokuapp.com/auth \
  -H 'Content-Type: application/json' \
  -d '{
    "username" : "admin",
    "password" : "password123"
}'
```

## Header

Название

Тип

Описание

Content-Type

string

Sets the format of payload you are sending

По умолчанию: `application/json`

## Request body

Название

Тип

Описание

username

String

Username for authentication

По умолчанию: `admin`

password

String

Password for authentication

По умолчанию: `password123`

## Success 200

Название

Тип

Описание

token

String

Token to use in future requests

-   [Response:](#success-examples-Auth-CreateToken-1_0_0-0)

```json
HTTP/1.1 200 OK

{
    "token": "abc123"
}
```

---

# Booking - GetBookingIds

-   [сравнить с:](#)
-   [1.0.0](#)

Returns the ids of all the bookings that exist within the API. Can take optional query strings to search and return a subset of booking ids.

get

```http
https://restful-booker.herokuapp.com/booking
```

-   [Example 1 (All IDs):](#examples-Booking-GetBookings-1_0_0-0)
-   [Example 2 (Filter by name):](#examples-Booking-GetBookings-1_0_0-1)
-   [Example 3 (Filter by checkin/checkout date):](#examples-Booking-GetBookings-1_0_0-2)

```json
curl -i https://restful-booker.herokuapp.com/booking
```

```json
curl -i https://restful-booker.herokuapp.com/booking?firstname=sally&lastname=brown
```

```json
curl -i https://restful-booker.herokuapp.com/booking?checkin=2014-03-13&checkout=2014-05-21
```

## Параметр

Название

Тип

Описание

firstname необязательный

String

Return bookings with a specific firstname

lastname необязательный

String

Return bookings with a specific lastname

checkin необязательный

date

Return bookings that have a checkin date greater than or equal to the set checkin date. Format must be CCYY-MM-DD

checkout необязательный

date

Return bookings that have a checkout date greater than or equal to the set checkout date. Format must be CCYY-MM-DD

## Success 200

Название

Тип

Описание

object

object\[\]

Array of objects that contain unique booking IDs

  bookingid

number

ID of a specific booking that matches search criteria

-   [Response:](#success-examples-Booking-GetBookings-1_0_0-0)

```json
HTTP/1.1 200 OK

[
  {
    "bookingid": 1
  },
  {
    "bookingid": 2
  },
  {
    "bookingid": 3
  },
  {
    "bookingid": 4
  }
]
```

---

# Booking

# Booking - GetBookingIds

-   [сравнить с:](#)
-   [1.0.0](#)

Returns the ids of all the bookings that exist within the API. Can take optional query strings to search and return a subset of booking ids.

get

```http
https://restful-booker.herokuapp.com/booking
```

-   [Example 1 (All IDs):](#examples-Booking-GetBookings-1_0_0-0)
-   [Example 2 (Filter by name):](#examples-Booking-GetBookings-1_0_0-1)
-   [Example 3 (Filter by checkin/checkout date):](#examples-Booking-GetBookings-1_0_0-2)

```json
curl -i https://restful-booker.herokuapp.com/booking
```

```json
curl -i https://restful-booker.herokuapp.com/booking?firstname=sally&lastname=brown
```

```json
curl -i https://restful-booker.herokuapp.com/booking?checkin=2014-03-13&checkout=2014-05-21
```

## Параметр

Название

Тип

Описание

firstname необязательный

String

Return bookings with a specific firstname

lastname необязательный

String

Return bookings with a specific lastname

checkin необязательный

date

Return bookings that have a checkin date greater than or equal to the set checkin date. Format must be CCYY-MM-DD

checkout необязательный

date

Return bookings that have a checkout date greater than or equal to the set checkout date. Format must be CCYY-MM-DD

## Success 200

Название

Тип

Описание

object

object\[\]

Array of objects that contain unique booking IDs

  bookingid

number

ID of a specific booking that matches search criteria

-   [Response:](#success-examples-Booking-GetBookings-1_0_0-0)

```json
HTTP/1.1 200 OK

[
  {
    "bookingid": 1
  },
  {
    "bookingid": 2
  },
  {
    "bookingid": 3
  },
  {
    "bookingid": 4
  }
]
```

# Booking - GetBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Returns a specific booking based upon the booking id provided

get

```http
https://restful-booker.herokuapp.com/booking/:id
```

-   [Example 1 (Get booking):](#examples-Booking-GetBooking-1_0_0-0)

```json
curl -i https://restful-booker.herokuapp.com/booking/1
```

## Header

Название

Тип

Описание

Accept

string

Sets what format the response body is returned in. Can be application/json or application/xml

По умолчанию: `application/json`

## Url Parameter

Название

Тип

Описание

id

String

The id of the booking you would like to retrieve

## Success 200

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

bookingdates

Object

Sub-object that contains the checkin and checkout dates

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

-   [JSON Response:](#success-examples-Booking-GetBooking-1_0_0-0)
-   [XML Response:](#success-examples-Booking-GetBooking-1_0_0-1)
-   [URL Response:](#success-examples-Booking-GetBooking-1_0_0-2)

```json
HTTP/1.1 200 OK

{
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
```

```xml
HTTP/1.1 200 OK

<booking>
    <firstname>Sally</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
        <checkin>2013-02-23</checkin>
        <checkout>2014-10-23</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
</booking>
```

```url
HTTP/1.1 200 OK

firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2019-01-01
```

# Booking - CreateBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Creates a new booking in the API

post

```http
https://restful-booker.herokuapp.com/booking
```

-   [JSON example usage:](#examples-Booking-CreateBooking-1_0_0-0)
-   [XML example usage:](#examples-Booking-CreateBooking-1_0_0-1)
-   [URLencoded example usage:](#examples-Booking-CreateBooking-1_0_0-2)

```json
curl -X POST \
  https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: application/json' \
  -d '{
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
```

```json
curl -X POST \
  https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: text/xml' \
  -d '<booking>
    <firstname>Jim</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
      <checkin>2018-01-01</checkin>
      <checkout>2019-01-01</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
  </booking>'
```

```json
curl -X POST \
  https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2018-01-02'
```

## Header

Название

Тип

Описание

Content-Type

string

Sets the format of payload you are sending. Can be application/json or text/xml

По умолчанию: `application/json`

Accept

string

Sets what format the response body is returned in. Can be application/json or application/xml

По умолчанию: `application/json`

## Request body

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

## Success 200

Название

Тип

Описание

bookingid

Number

ID for newly created booking

booking

Object

Object that contains

  firstname

String

Firstname for the guest who made the booking

  lastname

String

Lastname for the guest who made the booking

  totalprice

Number

The total price for the booking

  depositpaid

Boolean

Whether the deposit has been paid or not

  bookingdates

Object

Sub-object that contains the checkin and checkout dates

    checkin

Date

Date the guest is checking in

    checkout

Date

Date the guest is checking out

  additionalneeds

String

Any other needs the guest has

-   [JSON Response:](#success-examples-Booking-CreateBooking-1_0_0-0)
-   [XML Response:](#success-examples-Booking-CreateBooking-1_0_0-1)
-   [URL Response:](#success-examples-Booking-CreateBooking-1_0_0-2)

```json
HTTP/1.1 200 OK

{
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
```

```xml
HTTP/1.1 200 OK

<?xml version='1.0'?>
<created-booking>
    <bookingid>1</bookingid>
    <booking>
        <firstname>Jim</firstname>
        <lastname>Brown</lastname>
        <totalprice>111</totalprice>
        <depositpaid>true</depositpaid>
        <bookingdates>
            <checkin>2018-01-01</checkin>
            <checkout>2019-01-01</checkout>
        </bookingdates>
        <additionalneeds>Breakfast</additionalneeds>
    </booking>
</created-booking>
```

```url
HTTP/1.1 200 OK

bookingid=1&booking%5Bfirstname%5D=Jim&booking%5Blastname%5D=Brown&booking%5Btotalprice%5D=111&booking%5Bdepositpaid%5D=true&booking%5Bbookingdates%5D%5Bcheckin%5D=2018-01-01&booking%5Bbookingdates%5D%5Bcheckout%5D=2019-01-01
```

# Booking - UpdateBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Updates a current booking

put

```http
https://restful-booker.herokuapp.com/booking/:id
```

-   [JSON example usage:](#examples-Booking-UpdateBooking-1_0_0-0)
-   [XML example usage:](#examples-Booking-UpdateBooking-1_0_0-1)
-   [URLencoded example usage:](#examples-Booking-UpdateBooking-1_0_0-2)

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Cookie: token=abc123' \
  -d '{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
```

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: text/xml' \
  -H 'Accept: application/xml' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
  -d '<booking>
    <firstname>James</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
      <checkin>2018-01-01</checkin>
      <checkout>2019-01-01</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
  </booking>'
```

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Accept: application/x-www-form-urlencoded' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
  -d 'firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2018-01-02'
```

## Header

Название

Тип

Описание

Content-Type

string

Sets the format of payload you are sending. Can be application/json or text/xml

По умолчанию: `application/json`

Accept

string

Sets what format the response body is returned in. Can be application/json or application/xml

По умолчанию: `application/json`

Cookie необязательный

string

Sets an authorization token to access the PUT endpoint, can be used as an alternative to the Authorization

По умолчанию: `token=<token_value>`

Authorization необязательный

string

YWRtaW46cGFzc3dvcmQxMjM=\] Basic authorization header to access the PUT endpoint, can be used as an alternative to the Cookie header

По умолчанию: `Basic`

## Url Parameter

Название

Тип

Описание

id

Number

ID for the booking you want to update

## Request body

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

## Success 200

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

bookingdates

Object

Sub-object that contains the checkin and checkout dates

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

-   [JSON Response:](#success-examples-Booking-UpdateBooking-1_0_0-0)
-   [XML Response:](#success-examples-Booking-UpdateBooking-1_0_0-1)
-   [URL Response:](#success-examples-Booking-UpdateBooking-1_0_0-2)

```json
HTTP/1.1 200 OK

{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
```

```xml
HTTP/1.1 200 OK

<booking>
    <firstname>James</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
      <checkin>2018-01-01</checkin>
      <checkout>2019-01-01</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
</booking>
```

```url
HTTP/1.1 200 OK

firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2019-01-01
```

# Booking - PartialUpdateBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Updates a current booking with a partial payload

patch

```http
https://restful-booker.herokuapp.com/booking/:id
```

-   [JSON example usage:](#examples-Booking-PartialUpdateBooking-1_0_0-0)
-   [XML example usage:](#examples-Booking-PartialUpdateBooking-1_0_0-1)
-   [URLencoded example usage:](#examples-Booking-PartialUpdateBooking-1_0_0-2)

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Cookie: token=abc123' \
  -d '{
    "firstname" : "James",
    "lastname" : "Brown"
}'
```

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: text/xml' \
  -H 'Accept: application/xml' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
  -d '<booking>
    <firstname>James</firstname>
    <lastname>Brown</lastname>
  </booking>'
```

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Accept: application/x-www-form-urlencoded' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
  -d 'firstname=Jim&lastname=Brown'
```

## Header

Название

Тип

Описание

Content-Type

string

Sets the format of payload you are sending. Can be application/json or text/xml

По умолчанию: `application/json`

Accept

string

Sets what format the response body is returned in. Can be application/json or application/xml

По умолчанию: `application/json`

Cookie необязательный

string

Sets an authorization token to access the PUT endpoint, can be used as an alternative to the Authorization

По умолчанию: `token=<token_value>`

Authorization необязательный

string

YWRtaW46cGFzc3dvcmQxMjM=\] Basic authorization header to access the PUT endpoint, can be used as an alternative to the Cookie header

По умолчанию: `Basic`

## Url Parameter

Название

Тип

Описание

id

Number

ID for the booking you want to update

## Request body

Название

Тип

Описание

firstname необязательный

String

Firstname for the guest who made the booking

lastname необязательный

String

Lastname for the guest who made the booking

totalprice необязательный

Number

The total price for the booking

depositpaid необязательный

Boolean

Whether the deposit has been paid or not

  checkin необязательный

Date

Date the guest is checking in

  checkout необязательный

Date

Date the guest is checking out

additionalneeds необязательный

String

Any other needs the guest has

## Success 200

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

bookingdates

Object

Sub-object that contains the checkin and checkout dates

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

-   [JSON Response:](#success-examples-Booking-PartialUpdateBooking-1_0_0-0)
-   [XML Response:](#success-examples-Booking-PartialUpdateBooking-1_0_0-1)
-   [URL Response:](#success-examples-Booking-PartialUpdateBooking-1_0_0-2)

```json
HTTP/1.1 200 OK

{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
```

```xml
HTTP/1.1 200 OK

<booking>
    <firstname>James</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
      <checkin>2018-01-01</checkin>
      <checkout>2019-01-01</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
</booking>
```

```url
HTTP/1.1 200 OK

firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2019-01-01
```

# Booking - DeleteBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Deletes a booking from the API. Requires an authorization token to be set in the header or a Basic auth header.

delete

```http
https://restful-booker.herokuapp.com/booking/1
```

-   [Example 1 (Cookie):](#examples-Booking-DeleteBooking-1_0_0-0)
-   [Example 2 (Basic auth):](#examples-Booking-DeleteBooking-1_0_0-1)

```json
curl -X DELETE \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Cookie: token=abc123'
```

```json
curl -X DELETE \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM='
```

## Header

Название

Тип

Описание

Cookie необязательный

string

Sets an authorization token to access the DELETE endpoint, can be used as an alternative to the Authorization

По умолчанию: `token=<token_value>`

Authorization необязательный

string

YWRtaW46cGFzc3dvcmQxMjM=\] Basic authorization header to access the DELETE endpoint, can be used as an alternative to the Cookie header

По умолчанию: `Basic`

## Url Parameter

Название

Тип

Описание

id

Number

ID for the booking you want to update

## Success 200

Название

Тип

Описание

OK

String

Default HTTP 201 response

-   [Response:](#success-examples-Booking-DeleteBooking-1_0_0-0)

```json
HTTP/1.1 201 Created
```

---

# Booking - GetBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Returns a specific booking based upon the booking id provided

get

```http
https://restful-booker.herokuapp.com/booking/:id
```

-   [Example 1 (Get booking):](#examples-Booking-GetBooking-1_0_0-0)

```json
curl -i https://restful-booker.herokuapp.com/booking/1
```

## Header

Название

Тип

Описание

Accept

string

Sets what format the response body is returned in. Can be application/json or application/xml

По умолчанию: `application/json`

## Url Parameter

Название

Тип

Описание

id

String

The id of the booking you would like to retrieve

## Success 200

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

bookingdates

Object

Sub-object that contains the checkin and checkout dates

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

-   [JSON Response:](#success-examples-Booking-GetBooking-1_0_0-0)
-   [XML Response:](#success-examples-Booking-GetBooking-1_0_0-1)
-   [URL Response:](#success-examples-Booking-GetBooking-1_0_0-2)

```json
HTTP/1.1 200 OK

{
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
```

```xml
HTTP/1.1 200 OK

<booking>
    <firstname>Sally</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
        <checkin>2013-02-23</checkin>
        <checkout>2014-10-23</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
</booking>
```

```url
HTTP/1.1 200 OK

firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2019-01-01
```

---

# Booking - CreateBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Creates a new booking in the API

post

```http
https://restful-booker.herokuapp.com/booking
```

-   [JSON example usage:](#examples-Booking-CreateBooking-1_0_0-0)
-   [XML example usage:](#examples-Booking-CreateBooking-1_0_0-1)
-   [URLencoded example usage:](#examples-Booking-CreateBooking-1_0_0-2)

```json
curl -X POST \
  https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: application/json' \
  -d '{
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
```

```json
curl -X POST \
  https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: text/xml' \
  -d '<booking>
    <firstname>Jim</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
      <checkin>2018-01-01</checkin>
      <checkout>2019-01-01</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
  </booking>'
```

```json
curl -X POST \
  https://restful-booker.herokuapp.com/booking \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2018-01-02'
```

## Header

Название

Тип

Описание

Content-Type

string

Sets the format of payload you are sending. Can be application/json or text/xml

По умолчанию: `application/json`

Accept

string

Sets what format the response body is returned in. Can be application/json or application/xml

По умолчанию: `application/json`

## Request body

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

## Success 200

Название

Тип

Описание

bookingid

Number

ID for newly created booking

booking

Object

Object that contains

  firstname

String

Firstname for the guest who made the booking

  lastname

String

Lastname for the guest who made the booking

  totalprice

Number

The total price for the booking

  depositpaid

Boolean

Whether the deposit has been paid or not

  bookingdates

Object

Sub-object that contains the checkin and checkout dates

    checkin

Date

Date the guest is checking in

    checkout

Date

Date the guest is checking out

  additionalneeds

String

Any other needs the guest has

-   [JSON Response:](#success-examples-Booking-CreateBooking-1_0_0-0)
-   [XML Response:](#success-examples-Booking-CreateBooking-1_0_0-1)
-   [URL Response:](#success-examples-Booking-CreateBooking-1_0_0-2)

```json
HTTP/1.1 200 OK

{
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
```

```xml
HTTP/1.1 200 OK

<?xml version='1.0'?>
<created-booking>
    <bookingid>1</bookingid>
    <booking>
        <firstname>Jim</firstname>
        <lastname>Brown</lastname>
        <totalprice>111</totalprice>
        <depositpaid>true</depositpaid>
        <bookingdates>
            <checkin>2018-01-01</checkin>
            <checkout>2019-01-01</checkout>
        </bookingdates>
        <additionalneeds>Breakfast</additionalneeds>
    </booking>
</created-booking>
```

```url
HTTP/1.1 200 OK

bookingid=1&booking%5Bfirstname%5D=Jim&booking%5Blastname%5D=Brown&booking%5Btotalprice%5D=111&booking%5Bdepositpaid%5D=true&booking%5Bbookingdates%5D%5Bcheckin%5D=2018-01-01&booking%5Bbookingdates%5D%5Bcheckout%5D=2019-01-01
```

---

# Booking - UpdateBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Updates a current booking

put

```http
https://restful-booker.herokuapp.com/booking/:id
```

-   [JSON example usage:](#examples-Booking-UpdateBooking-1_0_0-0)
-   [XML example usage:](#examples-Booking-UpdateBooking-1_0_0-1)
-   [URLencoded example usage:](#examples-Booking-UpdateBooking-1_0_0-2)

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Cookie: token=abc123' \
  -d '{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'
```

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: text/xml' \
  -H 'Accept: application/xml' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
  -d '<booking>
    <firstname>James</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
      <checkin>2018-01-01</checkin>
      <checkout>2019-01-01</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
  </booking>'
```

```json
curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Accept: application/x-www-form-urlencoded' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=' \
  -d 'firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2018-01-02'
```

## Header

Название

Тип

Описание

Content-Type

string

Sets the format of payload you are sending. Can be application/json or text/xml

По умолчанию: `application/json`

Accept

string

Sets what format the response body is returned in. Can be application/json or application/xml

По умолчанию: `application/json`

Cookie необязательный

string

Sets an authorization token to access the PUT endpoint, can be used as an alternative to the Authorization

По умолчанию: `token=<token_value>`

Authorization необязательный

string

YWRtaW46cGFzc3dvcmQxMjM=\] Basic authorization header to access the PUT endpoint, can be used as an alternative to the Cookie header

По умолчанию: `Basic`

## Url Parameter

Название

Тип

Описание

id

Number

ID for the booking you want to update

## Request body

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

## Success 200

Название

Тип

Описание

firstname

String

Firstname for the guest who made the booking

lastname

String

Lastname for the guest who made the booking

totalprice

Number

The total price for the booking

depositpaid

Boolean

Whether the deposit has been paid or not

bookingdates

Object

Sub-object that contains the checkin and checkout dates

  checkin

Date

Date the guest is checking in

  checkout

Date

Date the guest is checking out

additionalneeds

String

Any other needs the guest has

-   [JSON Response:](#success-examples-Booking-UpdateBooking-1_0_0-0)
-   [XML Response:](#success-examples-Booking-UpdateBooking-1_0_0-1)
-   [URL Response:](#success-examples-Booking-UpdateBooking-1_0_0-2)

```json
HTTP/1.1 200 OK

{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
```

```xml
HTTP/1.1 200 OK

<booking>
    <firstname>James</firstname>
    <lastname>Brown</lastname>
    <totalprice>111</totalprice>
    <depositpaid>true</depositpaid>
    <bookingdates>
      <checkin>2018-01-01</checkin>
      <checkout>2019-01-01</checkout>
    </bookingdates>
    <additionalneeds>Breakfast</additionalneeds>
</booking>
```

```url
HTTP/1.1 200 OK

firstname=Jim&lastname=Brown&totalprice=111&depositpaid=true&bookingdates%5Bcheckin%5D=2018-01-01&bookingdates%5Bcheckout%5D=2019-01-01
```

---

# Booking - DeleteBooking

-   [сравнить с:](#)
-   [1.0.0](#)

Deletes a booking from the API. Requires an authorization token to be set in the header or a Basic auth header.

delete

```http
https://restful-booker.herokuapp.com/booking/1
```

-   [Example 1 (Cookie):](#examples-Booking-DeleteBooking-1_0_0-0)
-   [Example 2 (Basic auth):](#examples-Booking-DeleteBooking-1_0_0-1)

```json
curl -X DELETE \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Cookie: token=abc123'
```

```json
curl -X DELETE \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM='
```

## Header

Название

Тип

Описание

Cookie необязательный

string

Sets an authorization token to access the DELETE endpoint, can be used as an alternative to the Authorization

По умолчанию: `token=<token_value>`

Authorization необязательный

string

YWRtaW46cGFzc3dvcmQxMjM=\] Basic authorization header to access the DELETE endpoint, can be used as an alternative to the Cookie header

По умолчанию: `Basic`

## Url Parameter

Название

Тип

Описание

id

Number

ID for the booking you want to update

## Success 200

Название

Тип

Описание

OK

String

Default HTTP 201 response

-   [Response:](#success-examples-Booking-DeleteBooking-1_0_0-0)

```json
HTTP/1.1 201 Created
```

---

# Ping - HealthCheck

-   [сравнить с:](#)
-   [1.0.0](#)

A simple health check endpoint to confirm whether the API is up and running.

get

```http
https://restful-booker.herokuapp.com/ping
```

-   [Ping server:](#examples-Ping-Ping-1_0_0-0)

```json
curl -i https://restful-booker.herokuapp.com/ping
```

## Success 200

Название

Тип

Описание

OK

String

Default HTTP 201 response

-   [Response:](#success-examples-Ping-Ping-1_0_0-0)

```json
HTTP/1.1 201 Created
```
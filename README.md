# UtfDetailsService
## Functional Specification
Endpoint that returns the unicode, decimal representation and hexadecimal representation of an utf8 character

Examples:

Utf8 | Unicode | Decimal | Hex
---- | ------- | ------- | ---
£ (Pound Sign) | U+00A3 | 194 163 | c2 a3
𐀀 (LinearBSyllableB008A) | U+10000 | 240 144 128 128 | F0908080

You can invoke the endpoint with Swagger UI:

https://utf-details-service.herokuapp.com/swagger-ui.html#/utf-8-details-controller

## Technical Specification
Gradle 3 java web application.
* Spring boot (REST)
* Jackson
* Swagger
Build and run with
```
gradle clean build && java -jar ./build/libs/utfDetailsService-1.0-SNAPSHOT.jar
```
# Changelog
* 2013-02-27 Version 1.0 Initial version

# Areas of improvement



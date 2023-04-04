To run: mvn clean install spring-boot:run -e

Task 1:
GET localhost:9081/api/v1/users/73a749ec-5cc0-4a0c-b093-ea3a8815d6fc
POST localhost:9081/api/v1/users
{
  "email": "email4",
  "fullName": "fullName123",
  "birthDate": "2001-03-10",
  "gender": "MALE"
}

Task 2: CREATE INDEX email_idx ON users._default.users(email)
GET localhost:9081/api/v1/users/email/email2

Task 3:
PUT localhost:9081/api/v1/users/09f9422c-23c3-471e-af73-5a89b491a812/sport
{
    "sportName": "football123",
    "sportProficiency": "qwe123"
}

Task 4: CREATE INDEX sports_idx ON users._default.users(sports)
GET localhost:9081/api/v1/users/sport/football

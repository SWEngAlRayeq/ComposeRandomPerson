package app.random_person.data.model


data class RandomUserResponse(
    val results: List<RandomUser>
)

data class RandomUser(
    val name: Name,
    val picture: Picture,
    val dob: Dob,
    val nat: String
)

data class Name(val first: String, val last: String)
data class Picture(val large: String)
data class Dob(val age: String)
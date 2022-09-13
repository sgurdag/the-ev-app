package com.safagurdag.domain.model

data class User(
    val bookings: List<Booking>,
    val email: String,
    val profile: Profile,
    val userhours: List<Userhour>,
    val username: String
)

data class Booking(
    val car: Car,
    val car_class: CarClass,
    val id: Int,
    val real_start_time: String,
    val start_location: StartLocation,
    val start_time: String,
    val subscription_miles_left: String
)

data class Profile(
    val first_name: String,
    val hours_available: String,
    val last_name: String
)

data class Userhour(
    val car_class: Int,
    val hours_available: String
)

data class Car(
    val ev_access: List<String>,
    val id: Int,
    val image: String?,
    val last_energy_level: String,
    val make: String,
    val model: String,
    val real_world_range: Int,
    val registration_number: String
)

data class CarClass(
    val id: Int,
    val label: String
)

data class StartLocation(
    val id: Int,
    val name: String
)
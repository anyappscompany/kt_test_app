package ua.com.anyapps.kt_test_app.network.pojo

data class Books(
    val docs: List<Doc>,
    val numFound: Int,
    val num_found: Int,
    val start: Int
)
package com.kevin.twtoilet.Data

data class toiletData(
    val __extras: Extras,
    val _links: Links,
    val fields: List<Field>,
    val include_total: Boolean,
    val limit: Int,
    val offset: Int,
    val records: List<Record>,
    val records_format: String,
    val resource_id: String,
    val total: Int
)
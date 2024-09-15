package com.hetpatel.product_service.model;

public enum LeaseStatus {
    AVAILABLE,      // The item is available for lease or rent
    LEASED,         // The item has been leased or rented
    RETURNED,       // The item has been returned after lease/rental period
    CANCELLED
}

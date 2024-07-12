package org.system.librarymanagementsystemjava.responseEntity;

import java.time.Instant;

public record InventoryManagementResponseEntity(String status, String message, Instant time) {
}

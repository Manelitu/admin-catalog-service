package org.admin.catalog.domain.category;

import java.time.Instant;
import java.util.UUID;


public class Category {
   private String id;
   private String name;
   private String description;
   private Boolean active;
   private Instant createdAt;
   private Instant updatedAt;
   private Instant deletedAt;

   private Category(
           final String id,
           final String name,
           final String description,
           final Boolean active,
           final Instant createdAt,
           final Instant updatedAt,
           final Instant deletedAt
   ) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.active = active;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.deletedAt = deletedAt;
   }

   public static Category newCategory(final String name, final String description, final Boolean active) {
      final var id = UUID.randomUUID().toString();
      final var now = Instant.now();

      return new Category(id, name, description, active, now, now, null);
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public Boolean getActive() {
      return active;
   }

   public Instant getCreatedAt() {
      return createdAt;
   }

   public Instant getUpdatedAt() {
      return updatedAt;
   }

   public Instant getDeletedAt() {
      return deletedAt;
   }
}
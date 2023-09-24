package org.admin.catalog.domain.category;

import org.admin.catalog.domain.AggregateRoot;
import org.admin.catalog.domain.validation.CategoryValidator;
import org.admin.catalog.domain.validation.ValidationHandler;

import java.time.Instant;


public class Category extends AggregateRoot<CategoryID> {
   private String name;
   private String description;
   private Boolean active;
   private Instant createdAt;
   private Instant updatedAt;
   private Instant deletedAt;

   public Category(
           final CategoryID id,
           final String name,
           final String description,
           final Boolean active,
           final Instant createdAt,
           final Instant updatedAt,
           final Instant deletedAt
   ) {
      super(id);
      this.name = name;
      this.description = description;
      this.active = active;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.deletedAt = deletedAt;
   }

   public static Category newCategory(final String name, final String description, final Boolean active) {
      final var id = CategoryID.unique();
      final var now = Instant.now();
      final var deletedAt = active ? null : now;

      return new Category(id, name, description, active, now, now, deletedAt);
   }

   @Override
   public void validate(final ValidationHandler handler) {
      new CategoryValidator(this, handler).validate();
   }

   public Category deactivate() {
      if (getDeletedAt() == null) {
         this.deletedAt = Instant.now();
      }

      this.active = false;
      this.updatedAt = Instant.now();
      return this;
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
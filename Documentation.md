# Documentation

## Package javax.persistence

### Resources

- https://docs.oracle.com/javaee/7/tutorial/persistence-intro001.htm
- https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html

### Introduction

An entity is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table. The primary programming artifact of an entity is the entity class, although entities can use helper classes.

The persistent state of an entity is represented through either persistent fields or persistent properties. These fields or properties use object/relational mapping annotations to map the entities and entity relationships to the relational data in the underlying data store.

### Customer.java

- @Entity(name = "customer")
    - Create an entity name customer corresponding to the customer database table

- @ID
    - https://docs.oracle.com/javaee/7/api/javax/persistence/Id.html
    - Specifies the primary key of an entity.
- @GeneratedValue(strategy = GenerationType.IDENTITY)
    - https://docs.oracle.com/javaee/7/api/javax/persistence/GeneratedValue.html
    - https://docs.oracle.com/javaee/7/api/javax/persistence/GenerationType.html
    - Provides for the specification of generation strategies for the values of primary keys.
- @Column(name = "id", updatable = false, nullable = false)
    - https://docs.oracle.com/javaee/7/api/javax/persistence/Column.html
    - Specifies the mapped column for a persistent property or field. If no Column annotation is specified, the default values apply.
-  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    - https://docs.oracle.com/javaee/7/api/javax/persistence/OneToMany.html
    - Specifies a many-valued association with one-to-many multiplicity.
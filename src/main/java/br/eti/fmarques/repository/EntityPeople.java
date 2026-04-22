package br.eti.fmarques.repository;

import java.util.UUID;

import br.eti.fmarques.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.inject.Singleton;

@Singleton
public class EntityPeople implements PanacheRepositoryBase<Person, UUID> {  }

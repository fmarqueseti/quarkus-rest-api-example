package br.eti.fmarques.application;

import java.util.List;
import java.util.UUID;

import br.eti.fmarques.entity.Person;
import br.eti.fmarques.repository.EntityPeople;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("/people")
public class PeopleResource {
    
    @Inject
    private EntityPeople entityPeople;

    @POST
    @Transactional
    public Person createPerson(@Valid Person person) {
        this.entityPeople.persist(person);
        return person;
    }

    @GET
    @Path("/{id}")
    public Person findPersonById(@PathParam("id") UUID id) {
        return this.entityPeople.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletePerson(@PathParam("id") UUID id) {
        this.entityPeople.deleteById(id);
    }

    @PUT
    @Path("/{id}")
    public Person updatePerson(UUID id, Person person) {
        Person personById = this.entityPeople.findById(id);

        if (personById != null) {
            personById.setName(person.getName());
            personById.setEmail(person.getEmail());
            personById.setBirthDay(person.getBirthDay());

            this.entityPeople.persist(personById);
        }

        return personById;
    }

    @GET
    public List<Person> listPeople(@QueryParam("page") @DefaultValue("0") int pageIndex,
                                   @QueryParam("size") @DefaultValue("20") int pageSize) {
        return this.entityPeople.findAll().page(Page.of(0, pageSize)).list();
    }
}

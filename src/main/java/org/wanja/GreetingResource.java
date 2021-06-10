package org.wanja;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.wanja.entity.County;
import org.wanja.entity.State;

import io.quarkus.panache.common.Sort;

@Path("/t")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class GreetingResource {

    @GET
    @Path("/counties")
    public List<County> counties() {
        List<County> list = County.listAll(Sort.by("state_name").and("name"));
        return list;
    }

    @GET
    @Path("/states")
    public String states() {
        List<State> list = State.listAll(Sort.by("name"));
        StringBuilder sb = new StringBuilder();
        for (State c : list) {
            sb.append(c.name).append("\n");
        }
        return sb.toString();
    }

    @GET
    @Path("cbs/{state}")
    public List<County> countiesByState(@PathParam String state) {
        System.out.println("We got: "+ state);
        List<County> list = County.findByState(state);
        return list;
    }

    @GET
    @Path("sbn/{name}")
    public List<State> statesByName(@PathParam String name) {
        System.out.println("We got: " + name);
        return State.findByName(name);
    }

    @GET
    @Path("sbb/{name}")
    public List<County> countiesByName(@PathParam String name) {
        System.out.println("We got: " + name);
        return County.findByName(name);
    }

}